/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.service;

import com.ivt.spring_project_internship_tantubank.entities.BankAccountEntity;
import com.ivt.spring_project_internship_tantubank.entities.BankEntity;
import com.ivt.spring_project_internship_tantubank.entities.CustomerEntity;
import com.ivt.spring_project_internship_tantubank.entities.StaffEntity;
import com.ivt.spring_project_internship_tantubank.entities.TransactionEntity;
import com.ivt.spring_project_internship_tantubank.enums.AccountStatus;
import com.ivt.spring_project_internship_tantubank.enums.AccountType;
import com.ivt.spring_project_internship_tantubank.enums.TransactionType;
import com.ivt.spring_project_internship_tantubank.model.FilterWithSortBy;
import com.ivt.spring_project_internship_tantubank.repository.BankAccountRepository;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;
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
    private StaffService staffService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    HttpSession session;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Transactional
    public boolean editBankAccount(Model model, BankAccountEntity bankAccount, 
            boolean physicalCard,
            String editStaffId, String buttonEdit) throws MessagingException {
        if (editStaffId.equals("")) {
            model.addAttribute("messageStaff", "Staff không được để trống");
            return false;
        } else {
            StaffEntity editStaff = staffService.findStaffById(model, Long.parseLong(editStaffId));
            if (editStaff.getId() > 0) {
                model.addAttribute("editStaff", editStaff);
                if (buttonEdit != null) {
                    bankAccount.setPhysicalCard(physicalCard);
                    bankAccount.setTantuBankAddress(bankAccount.getTantuBankAddress());
                    saveOrUpdateBankAccount(bankAccount);

                    TransactionEntity transaction = new TransactionEntity();
                    transaction.setTransactionAmount(0);
                    transaction.setTransactionContent("TanTuBank chỉnh sửa tài khoản ngân hàng cho quý khách");
                    transaction.setTransactionDate(Timestamp.valueOf(sdf.format(new Date())));
                    transaction.setBankAccount2(bankAccount);
                    transaction.setFeeTransaction(0);
                    transaction.setTransactionType(TransactionType.EDIT_BANK_ACCOUNT_BY_STAFF);
                    transaction.setStaff(editStaff);
                    transactionService.saveOrUpdateTransaction(transaction);

                    String subject = "Chỉnh sửa tài khoản ngân hàng thành công!";
                    String content = "<h1>Số tài khoản: " + bankAccount.getAccountNumber() + "</h1>"
                            + "<h1>Chủ sở hữu " + bankAccount.getAccountName() + "<h1>"
                            + "<h3>đã được chỉnh sửa tại ngân hàng TanTuBank</h3>"
                            + "<h1>Để biết thêm chi tiết, bạn vui lòng click vào đường link bên dưới: </h1>"
                            + "<h3><a href='http://localhost:8080/Spring_Project_Internship_TanTuBank/'>TanTuBank</a></h3>"
                            + "<h3>Cảm ơn bạn! Love 3000 nè!</h3>";
                    transactionService.sendMail("natsutan94@gmail.com", bankAccount.getCustomer().getCustomerEmail(), subject, content);
                    return true;
                }
            }
            return false;
        }
    }

    @Transactional
    public boolean openBankAccount(Model model, BankAccountEntity bankAccount,
            String customerPhone, String customerEmail, String staffId,
            boolean physicalCard, String buttonOpen) throws MessagingException {
        if (customerPhone.matches("^(\\d{10,12})$") == false) {
            model.addAttribute("messageCustomerPhone", "CustomerPhone phải là số có từ 10 đến 12 chữ số");
            return false;
        } else {
            if (customerEmail.matches("^[a-zA-Z0-9]{1,30}+@[a-zA-Z0-9]{2,10}+\\.[a-zA-Z]{2,10}$") == false) {
                model.addAttribute("messageCustomerEmail", "CustomerEmail không đúng định dạng");
                return false;
            } else {
                CustomerEntity customer = customerService.findByCustomerEmailOrCustomerPhone(customerPhone, customerEmail);
                if (customer.getId() <= 0) {
                    model.addAttribute("messageOpenBA", "Khách hàng không tồn tại");
                    return false;
                } else if (!customer.getBankAccounts().isEmpty()) {
                    model.addAttribute("messageOpenBA", "Tài khoản ngân hàng đã tồn tại");
                    return false;
                } else {
                    model.addAttribute("customer", customer);
                    if (staffId.equals("")) {
                        model.addAttribute("messageStaff", "Staff không được để trống");
                        return false;
                    } else {
                        StaffEntity findStaff = staffService.findStaffById(model, Long.parseLong(staffId));
                        if (findStaff.getId() > 0) {
                            model.addAttribute("staff", findStaff);
                            if (buttonOpen != null) {
                                bankAccount.setAccountName(customer.getCustomerName());
                                String accountNumber = RandomStringUtils.randomNumeric(16);
                                bankAccount.setAccountNumber(accountNumber);
                                bankAccount.setBalance(50000);
                                bankAccount.setCreateDate(Timestamp.valueOf(sdf.format(new Date())));
                                BankEntity bank = new BankEntity();
                                bank.setId(1);
                                bankAccount.setBank(bank);
                                bankAccount.setCustomer(customer);
                                bankAccount.setBankAccountStatus(AccountStatus.ACTIVE);
                                StaffEntity staff = new StaffEntity();
                                staff.setId(Long.parseLong(staffId));
                                bankAccount.setStaff(staff);
                                bankAccount.setPhysicalCard(physicalCard);
                                saveOrUpdateBankAccount(bankAccount);

                                TransactionEntity transaction = new TransactionEntity();
                                if (physicalCard == true) {
                                    transaction.setTransactionAmount(50000);
                                } else {
                                    transaction.setTransactionAmount(0);
                                }
                                transaction.setTransactionContent("TanTuBank mở tài khoản ngân hàng cho quý khách");
                                transaction.setTransactionDate(Timestamp.valueOf(sdf.format(new Date())));
                                transaction.setTransactionType(TransactionType.OPEN_BANK_ACCOUNT_BY_STAFF);
                                transaction.setBankAccount2(bankAccount);
                                transaction.setFeeTransaction(0);
                                transaction.setStaff(staff);
                                transactionService.saveOrUpdateTransaction(transaction);

                                String subject = "Mở tài khoản ngân hàng thành công!";
                                String content = "<h1>Số tài khoản: " + bankAccount.getAccountNumber() + "</h1>"
                                        + "<h1>Chủ sở hữu " + bankAccount.getAccountName() + "<h1>"
                                        + "<h3>đã được cấp quyền sử dụng tại ngân hàng TanTuBank</h3>"
                                        + "<h1>Để biết thêm chi tiết, bạn vui lòng click vào đường link bên dưới: </h1>"
                                        + "<h3><a href='http://localhost:8080/Spring_Project_Internship_TanTuBank/'>TanTuBank</a></h3>"
                                        + "<h3>Cảm ơn bạn! Love 3000 nè!</h3>";
                                transactionService.sendMail("natsutan94@gmail.com", customerEmail, subject, content);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void deleteBankAccount(long bankAccountId) {
        bankAccountRepository.deleteById(bankAccountId);
    }

    public void changeBankAccountStatus(BankAccountEntity bankAccount, AccountStatus bankAccountStatus) {
        bankAccount.setBankAccountStatus(bankAccountStatus);
        bankAccountRepository.save(bankAccount);
    }

    public BankAccountEntity findBAById(long bankAccountId) {
        Optional<BankAccountEntity> bankAccountOpt = bankAccountRepository.findById(bankAccountId);
        if (bankAccountOpt != null && bankAccountOpt.isPresent()) {
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
            return bankAccounts;
        }
        return null;
    }

    @Transactional
    public Page<BankAccountEntity> findBAByTypeAndNotTanTuBank(String accountType, long bankId, int currentPage, int pageSize, Sort sort) {
        Page<BankAccountEntity> bankAccounts = bankAccountRepository.findByAccountTypeAndBankIdNot(accountType, bankId, PageRequest.of(currentPage, pageSize, sort));
        if (!bankAccounts.isEmpty()) {
            return bankAccounts;
        }
        return null;
    }

    @Transactional
    public Page<BankAccountEntity> findBAByBankAndStatus(long bankId, AccountStatus accountStatus, int currentPage, int pageSize, Sort sort) {
        Page<BankAccountEntity> bankAccounts = bankAccountRepository.findByBankIdAndBankAccountStatus(bankId, accountStatus, PageRequest.of(currentPage, pageSize, sort));
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
