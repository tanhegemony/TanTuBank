/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.controller;

import com.ivt.spring_project_internship_tantubank.entities.BankAccountEntity;
import com.ivt.spring_project_internship_tantubank.entities.StaffEntity;
import com.ivt.spring_project_internship_tantubank.entities.TransactionEntity;
import com.ivt.spring_project_internship_tantubank.enums.AccountType;
import com.ivt.spring_project_internship_tantubank.enums.TransactionType;
import com.ivt.spring_project_internship_tantubank.service.BankAccountService;
import com.ivt.spring_project_internship_tantubank.service.StaffService;
import com.ivt.spring_project_internship_tantubank.service.TransactionService;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author TanHegemony
 */
@Controller
@RequestMapping("/management/")
public class DepositController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpSession session;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private StaffService staffService;

    @RequestMapping("depositForCustomer")
    public String viewDeposit(Model model) {
        model.addAttribute("action", "deposit_for_customer");

        String captcha = RandomStringUtils.randomAlphanumeric(6);
        session.setAttribute("captcha", captcha);

        model.addAttribute("makeDeposit", true);
        model.addAttribute("completeDeposit", false);
        session.setAttribute("balanceTransaction", new String());
        session.setAttribute("contentTransaction", new String());
        session.setAttribute("transaction", new TransactionEntity());
        session.setAttribute("staff", new StaffEntity());
        session.setAttribute("receiveBankAccount", new BankAccountEntity());
        return "/management/deposit";
    }

    @RequestMapping(value = "resultMakeDeposit", method = RequestMethod.POST)
    public String resultMakeDeposit(Model model) throws MessagingException {
        model.addAttribute("action", "deposit_for_customer");
        String depositAccountNumber = request.getParameter("depositAccountNumber");
        BankAccountEntity receiveBankAccount = bankAccountService.findBankAccountByTypeAndAccountNumberAndBank(
                model, AccountType.PAYMENT_ACCOUNT.toString(), depositAccountNumber, 1);
        if (receiveBankAccount.getId() > 0) {
            String balanceTransaction = request.getParameter("balanceDeposit");
            balanceTransaction = balanceTransaction.replaceAll(",", "");
            String contentTransaction = "TanTuBank nap tien cho quy khach";
            String captcha = request.getParameter("captcha");

            String staffId = request.getParameter("staffId");
            if (staffId.equals("")) {
                staffId = "0";
            }
            StaffEntity staff = staffService.findStaffById(model, Long.parseLong(staffId));

            boolean checkTrans = transactionService.checkTransaction(
                    model, "deposit_for_customer", contentTransaction,
                    balanceTransaction, receiveBankAccount,
                    captcha, receiveBankAccount.getCustomer().getCustomerEmail());
            session.setAttribute("balanceTransaction", balanceTransaction);
            session.setAttribute("contentTransaction", contentTransaction);
            if (checkTrans == true) {
                TransactionEntity transaction = new TransactionEntity();
                transaction.setTransactionType(TransactionType.DEPOSIT_PAYMENT_ACCOUNT);
                transactionService.makeTransfer("deposit_for_customer", transaction);
                session.setAttribute("transaction", transaction);
                return "redirect:/management/viewCompleteDeposit";
            }
            
            session.setAttribute("staff", staff);
            model.addAttribute("staffId", staffId);
            session.setAttribute("receiveBankAccount", receiveBankAccount);
        }
        model.addAttribute("depositAccountNumber", depositAccountNumber);
        model.addAttribute("makeDeposit", true);
        model.addAttribute("completeDeposit", false);
        return "/management/deposit";
    }

    //change captcha with AJAX
    @RequestMapping(value = "changeCaptDeposit", method = RequestMethod.GET)
    public @ResponseBody
    String ajaxChangeCaptcha(Model model) {
        String reloadCaptcha = RandomStringUtils.randomAlphanumeric(6);
        session.setAttribute("captcha", reloadCaptcha);
        return reloadCaptcha;
    }

    @RequestMapping("viewCompleteDeposit")
    public String viewCompleteDepositI(Model model) {
        model.addAttribute("action", "deposit_for_customer");
        model.addAttribute("makeDeposit", false);
        model.addAttribute("completeDeposit", true);
        return "/management/deposit";
    }
}
