/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.controller;

import com.fasterxml.jackson.core.JsonEncoding;
import com.ivt.spring_project_internship_tantubank.entities.BankAccountEntity;
import com.ivt.spring_project_internship_tantubank.entities.TransactionEntity;
import com.ivt.spring_project_internship_tantubank.enums.AccountType;
import com.ivt.spring_project_internship_tantubank.enums.TransactionType;
import com.ivt.spring_project_internship_tantubank.service.BankAccountService;
import com.ivt.spring_project_internship_tantubank.service.TransactionService;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author TanHegemony
 */
@Controller
public class InternalTransferController {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpSession session;

    public void resetSessionTransactionAfter() {
        session.setAttribute("receiveAccount", new String());
        session.setAttribute("receiveBankAccount", new BankAccountEntity());
        session.setAttribute("balanceTransfer", new String());
        session.setAttribute("captcha", new String());
        session.setAttribute("confirmCode", new String());
        session.setAttribute("contentTransaction", new String());
    }

    @RequestMapping("viewInternalTransfer")
    public String viewTransfer(Model model) {
        //set action
        model.addAttribute("action", "internal_transfer");
        //find bank account original
        BankAccountEntity bankAccount = bankAccountService.findBankAccountByTypeAndCustomer(
                AccountType.PAYMENT_ACCOUNT.toString(), 1);
        session.setAttribute("bankAccount", bankAccount);
        // create auto captcha
        String captcha = RandomStringUtils.randomAlphanumeric(6);
        session.setAttribute("captcha", captcha);

        model.addAttribute("prepareIT", true);
        model.addAttribute("makeIT", false);
        model.addAttribute("completeIT", false);

        // make empty session
        session.setAttribute("receiveAccount", new String());
        session.setAttribute("receiveBankAccount", new BankAccountEntity());
        session.setAttribute("balanceTransfer", new String());
        session.setAttribute("confirmCode", new String());
        session.setAttribute("contentTransaction", new String());
        return "/user/internal_transfer";
    }

    @RequestMapping(value = "checkEnterReceiveAccount", method = RequestMethod.POST)
    public String checkEnterReceiveAccount(Model model) {
        model.addAttribute("action", "internal_transfer");
        String receiveAccount = request.getParameter("receiveAccount");
        //find entered receive account
        BankAccountEntity receiveBankAccount = bankAccountService.findBankAccountByTypeAndAccountNumberAndBank(model, AccountType.PAYMENT_ACCOUNT.toString(), receiveAccount, 1);

        session.setAttribute("receiveAccount", receiveAccount);
        session.setAttribute("receiveBankAccount", receiveBankAccount);
        model.addAttribute("prepareIT", true);
        model.addAttribute("makeIT", false);
        model.addAttribute("completeIT", false);
        return "/user/internal_transfer";
    }

    @RequestMapping(value = "resultPrepareInternalTransfer", method = RequestMethod.POST)
    public String resultPrepareInternalTransfer(Model model) throws MessagingException {
        model.addAttribute("action", "internal_transfer");
        if (session.getAttribute("receiveAccount").equals("")) {
            model.addAttribute("messageReceiveAccountNumber", "Bạn chưa nhập tài khoản nhận!");
        } else {
            // get Bank Account Original
            BankAccountEntity bankAccount
                    = (BankAccountEntity) session.getAttribute("bankAccount");
            // enter fields
            String balanceTransaction = request.getParameter("balanceTransfer");
            balanceTransaction = balanceTransaction.replaceAll(",", "");
            String contentTransaction = request.getParameter("contentTransfer");
            String captcha = request.getParameter("captcha");
            //check enter captcha and sendmail
            boolean checkTrans = transactionService.checkTransaction(
                    model, "internal_transfer",contentTransaction, 
                    balanceTransaction, bankAccount, captcha,
                    bankAccount.getCustomer().getCustomerEmail());
            session.setAttribute("balanceTransaction", balanceTransaction);
            session.setAttribute("contentTransaction", contentTransaction);
            if (checkTrans == true) {
                return "redirect:/viewConfirmIT";
            }
        }
        model.addAttribute("prepareIT", true);
        model.addAttribute("makeIT", false);
        model.addAttribute("completeIT", false);
        return "/user/internal_transfer";
    }

    //change captcha with AJAX
    @RequestMapping(value = "changeCapt", method = RequestMethod.GET)
    public @ResponseBody
    String ajaxChangeCaptcha(Model model) {
        String reloadCaptcha = RandomStringUtils.randomAlphanumeric(6);
        session.setAttribute("captcha", reloadCaptcha);
        return reloadCaptcha;
    }

    @RequestMapping("viewConfirmIT")
    public String viewConfirmEmailInternalTransfer(Model model) {
        model.addAttribute("action", "internal_transfer");
        model.addAttribute("prepareIT", false);
        model.addAttribute("makeIT", true);
        model.addAttribute("completeIT", false);
        return "/user/internal_transfer";
    }

    @RequestMapping(value = "resultMakeInternalTransfer", method = RequestMethod.POST)
    public String resultMakeInternalTransfer(Model model) {
        model.addAttribute("action", "internal_transfer");
        String confirmCode = request.getParameter("confirmCode");
        boolean checkCCode = transactionService.checkConfirmCode(model, confirmCode);
        if (checkCCode == false) {
            model.addAttribute("makeIT", true);
            model.addAttribute("completeIT", false);
        } else {
            TransactionEntity transaction = new TransactionEntity();
            transaction.setTransactionType(TransactionType.INTERNAL_TRANSFER_PAYMENT_ACCOUNT);
            transactionService.makeTransfer("internal_transfer", transaction);
            model.addAttribute("makeIT", false);
            model.addAttribute("completeIT", true);
            resetSessionTransactionAfter();
        }
        model.addAttribute("prepareIT", false);
        return "/user/internal_transfer";
    }

}
