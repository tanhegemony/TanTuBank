/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.service;

import com.ivt.spring_project_internship_tantubank.entities.BankAccountEntity;
import com.ivt.spring_project_internship_tantubank.enums.AccountStatus;
import com.ivt.spring_project_internship_tantubank.enums.AccountType;
import com.ivt.spring_project_internship_tantubank.model.FilterWithSortBy;
import com.ivt.spring_project_internship_tantubank.repository.BankAccountRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

/**
 *
 * @author TanHegemony
 */
@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    HttpSession session;

    public void changeBankAccountStatus(BankAccountEntity bankAccount, AccountStatus bankAccountStatus){
        bankAccount.setBankAccountStatus(bankAccountStatus);
        bankAccountRepository.save(bankAccount);
    }
    
    public BankAccountEntity findBAById(long bankAccountId){
        Optional<BankAccountEntity> bankAccountOpt = bankAccountRepository.findById(bankAccountId);
        if(bankAccountOpt != null && bankAccountOpt.isPresent()){
            return bankAccountOpt.get();
        }
        return new BankAccountEntity();
    }
    
    
    // tạo lại các phương thức để nhận bank accounts thực hiện trong filter
    // create again methods for receive bankAccounts make in filter
    public Page<BankAccountEntity> getBankAccountsMakeInFilter(String nav, String searchValue, int page, int pageSize, Sort sort) {
        Page<BankAccountEntity> bankAccounts = null;
        if (searchValue.equals("")) {
            if (nav.equals("managePaymentBankAccount")) {
                bankAccounts = findBAByTypeAndBank(
                        AccountType.PAYMENT_ACCOUNT.toString(), 1, page - 1, pageSize, sort);
            } else if (nav.equals("manageSavingBankAccount")) {
                bankAccounts = findBAByTypeAndBank(
                        AccountType.SAVING_ACCOUNT.toString(), 1, page - 1, pageSize, sort);
            } else if (nav.equals("manageExternalBankAccount")) {
                bankAccounts = findBAByTypeAndNotTanTuBank(
                        AccountType.PAYMENT_ACCOUNT.toString(), 1, page - 1, pageSize, sort);
            }
        } else {
            if (nav.equals("managePaymentBankAccount")) {
                bankAccounts = searchBankAccount(AccountType.PAYMENT_ACCOUNT.toString(),
                        1, "%" + searchValue + "%", page - 1, pageSize, sort);
            } else if (nav.equals("manageSavingBankAccount")) {
                bankAccounts = searchBankAccount(AccountType.SAVING_ACCOUNT.toString(),
                        1, "%" + searchValue + "%", page - 1, pageSize, sort);
            } else if (nav.equals("manageExternalBankAccount")) {
                bankAccounts = searchBankAccountNotTanTuBank(AccountType.PAYMENT_ACCOUNT.toString(),
                        1, "%" + searchValue + "%", page - 1, pageSize, sort);
            }
        }

        return bankAccounts;
    }

    public boolean resultFilter(Model model,
            String nav,
            String searchValue,
            String startDate, String endDate, String accountStatus, String sortBy,
            int currentPage, int pageSize,
            int totalPage) {
        // set list again for sort
        Sort sort = FilterWithSortBy.resultSortBy(sortBy);
        Page<BankAccountEntity> bankAccounts = getBankAccountsMakeInFilter(nav, searchValue, currentPage, pageSize, sort);
        if (bankAccounts == null) {
            return false;
        } else {
            for (BankAccountEntity ba : bankAccounts.getContent()) {
                System.out.println("H1" + ba.getAccountName());
            }
            // if select only startDate
            if ((!startDate.equals("") || startDate.equals(""))
                    && (endDate.equals("") && accountStatus.equals(""))) {
                totalPage = bankAccounts.getTotalPages();
                model.addAttribute("bankAccounts", bankAccounts);
            } else {
                // set startdate if empty
                if (startDate.equals("")) {
                    startDate = endDate;
                }
                // convert date
                String startDateConvert = startDate + " 00:00:00";
                String endDateConvert = endDate + " 23:59:59";
                // create list for handle filter by lambda
                List<BankAccountEntity> filterBankAccountList = new ArrayList<>();
                List<BankAccountEntity> filterBankAccount = new ArrayList<>();
                // run only filter for add all 1 list -> pagination again
                int i = 1;
                double runFilter = (double) bankAccounts.getTotalPages() / pageSize;
                if (runFilter > 0 && runFilter < 1) {
                    runFilter = 1;
                }
                while (runFilter >= i) {
                    bankAccounts = getBankAccountsMakeInFilter(nav, searchValue, i, pageSize, sort);
                    filterBankAccount = bankAccounts.stream().filter(ba -> !endDate.equals("") ? (ba.getCreateDate().toString().compareTo(startDateConvert) >= 0
                            && ba.getCreateDate().toString().compareTo(endDateConvert) < 0) : true)
                            .filter(ba -> !accountStatus.equals("") ? ba.getBankAccountStatus().toString().equals(accountStatus) : true)
                            .filter(ba -> !searchValue.equals("") ? ba.getAccountName().contains(searchValue) : true)
                            .collect(Collectors.toList());

                    filterBankAccountList.addAll(filterBankAccount);
                    i++;
                }
                // set first and end for create sub list
                // set first 
                int start = (int) PageRequest.of(currentPage - 1, pageSize).getOffset();
                // set end
                int end = start + PageRequest.of(currentPage - 1, pageSize).getPageSize();
                if (end > filterBankAccountList.size()) {
                    end = filterBankAccountList.size();
                }
                // pagination again
                Page<BankAccountEntity> resultBankAccounts = new PageImpl<BankAccountEntity>(
                        filterBankAccountList.subList(start, end),
                        PageRequest.of(currentPage - 1, pageSize),
                        filterBankAccountList.size());
                totalPage = resultBankAccounts.getTotalPages();
                model.addAttribute("bankAccounts", resultBankAccounts);
            }
            model.addAttribute("totalPage", totalPage);
        }

        return true;
    }

    @Transactional
    public Page<BankAccountEntity> searchBankAccount(String accountType, long bankId, String searchValue, int currentPage, int pageSize, Sort sort) {
        Page<BankAccountEntity> bankAccounts = bankAccountRepository.
                searchBankAccountByNameAndPhone(
                        accountType, bankId, searchValue, searchValue, PageRequest.of(currentPage, pageSize, sort));
        if (!bankAccounts.isEmpty()) {
            for (BankAccountEntity ba : bankAccounts.getContent()) {
                Hibernate.initialize(ba.getCustomer().getUser().getUserRoles());
            }
            return bankAccounts;
        }
        return null;
    }

    @Transactional
    public Page<BankAccountEntity> searchBankAccountNotTanTuBank(String accountType, long bankId, String searchValue, int currentPage, int pageSize, Sort sort) {
        Page<BankAccountEntity> bankAccounts = bankAccountRepository.
                searchBankAccountByNameAndPhoneNotTanTuBank(
                        accountType, bankId, searchValue, searchValue, PageRequest.of(currentPage, pageSize, sort));
        if (!bankAccounts.isEmpty()) {
            for (BankAccountEntity ba : bankAccounts.getContent()) {
                Hibernate.initialize(ba.getCustomer().getUser().getUserRoles());
            }
            return bankAccounts;
        }
        return null;
    }

    @Transactional
    public Page<BankAccountEntity> findBAByTypeAndBank(String accountType, long bankId, int currentPage, int pageSize, Sort sort) {
        Page<BankAccountEntity> bankAccounts = bankAccountRepository.findByAccountTypeAndBankId(accountType, bankId, PageRequest.of(currentPage, pageSize, sort));
        if (!bankAccounts.isEmpty()) {
            for (BankAccountEntity ba : bankAccounts.getContent()) {
                Hibernate.initialize(ba.getCustomer().getUser().getUserRoles());
            }
            return bankAccounts;
        }
        return null;
    }

    @Transactional
    public Page<BankAccountEntity> findBAByTypeAndNotTanTuBank(String accountType, long bankId, int currentPage, int pageSize, Sort sort) {
        Page<BankAccountEntity> bankAccounts = bankAccountRepository.findByAccountTypeAndBankIdNot(accountType, bankId, PageRequest.of(currentPage, pageSize, sort));
        System.out.println(bankAccounts.getContent());
        if (!bankAccounts.isEmpty()) {
            return bankAccounts;
        }
        return null;
    }

    // save or update bankaccount
    public void saveOrUpdateBankAccount(BankAccountEntity bankAccount) {
        bankAccount.setAccountNumber(bankAccount.getAccountNumber().replaceAll(" ", ""));
        bankAccountRepository.save(bankAccount);
    }

    // show bank acc by account type and customer id
    public BankAccountEntity findBankAccountByTypeAndCustomer(String accountType, long customerId) {
        BankAccountEntity bankAccount = bankAccountRepository.findByAccountTypeAndCustomerId(accountType, customerId);
        if (bankAccount != null) {
            StringBuilder sb = new StringBuilder(bankAccount.getAccountNumber());
            sb = sb.insert(4, " ");
            sb = sb.insert(9, " ");
            sb = sb.insert(14, " ");
            bankAccount.setAccountNumber(sb.toString());
            return bankAccount;
        }
        return new BankAccountEntity();
    }

    // check bank acc by account type and receive account number
    public BankAccountEntity findBankAccountByTypeAndAccountNumberAndBank(Model model, String accountType, String receiveAccount, long bankId) {
        if (bankId == 0) {
            model.addAttribute("messageBank", "Bạn vui lòng chọn bank!");
        } else {
            if (receiveAccount.equals("")) {
                model.addAttribute("messageReceiveAccountNumber", "ReceiveAccount không được để trống!");
            } else {
                if (StringUtils.isNumeric(receiveAccount) == false || receiveAccount.length() != 16) {
                    model.addAttribute("messageReceiveAccountNumber", "ReceiveAccount phải là số có 16 số");
                } else {
                    BankAccountEntity bankAccount = bankAccountRepository.findByAccountTypeAndAccountNumberAndBankId(accountType, receiveAccount, bankId);
                    if (bankAccount != null) {
                        StringBuilder sb = new StringBuilder(bankAccount.getAccountNumber());
                        sb = sb.insert(4, " ");
                        sb = sb.insert(9, " ");
                        sb = sb.insert(14, " ");
                        bankAccount.setAccountNumber(sb.toString());
                        return bankAccount;
                    } else {
                        model.addAttribute("messageReceiveAccountNumber", "Tài khoản này không tồn tại!");
                    }
                }

            }
        }

        return new BankAccountEntity();
    }
}
