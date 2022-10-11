/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.service;

import com.ivt.spring_project_internship_tantubank.entities.BankAccountEntity;
import com.ivt.spring_project_internship_tantubank.enums.AccountType;
import com.ivt.spring_project_internship_tantubank.repository.BankAccountRepository;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
