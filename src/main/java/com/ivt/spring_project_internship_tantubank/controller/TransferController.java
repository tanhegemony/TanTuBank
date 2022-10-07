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
import javax.mail.MessagingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
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
public class TransferController {

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
        session.setAttribute("bankReceiveAccount", new String());
        session.setAttribute("balanceTransfer", new String());
        session.setAttribute("captcha", new String());
        session.setAttribute("confirmCode", new String());
    }
    
    @RequestMapping("viewInternalTransfer")
    public String viewTransfer(Model model) {
        BankAccountEntity bankAccount = bankAccountService.findBankAccountByTypeAndCustomer(AccountType.PAYMENT_ACCOUNT.toString(), 1);
        session.setAttribute("bankReceiveAccount", new BankAccountEntity());
        session.setAttribute("bankAccount", bankAccount);
        model.addAttribute("prepareIT", true);
        model.addAttribute("makeIT", false);
        model.addAttribute("completeIT", false);
        String captcha = RandomStringUtils.randomAlphanumeric(6);
        session.setAttribute("captcha", captcha);
        resetSessionTransactionAfter();
        return "/user/internal_transfer";
    }

    @RequestMapping(value = "checkEnterReceiveAccount", method = RequestMethod.POST)
    public String checkEnterReceiveAccount(Model model) {
        String receiveAccount = request.getParameter("receiveAccount");
        //find entered receive account
        BankAccountEntity bankReceiveAccount = bankAccountService.findBankAccountByTypeAndAccountNumber(model, AccountType.PAYMENT_ACCOUNT.toString(), receiveAccount);
        session.setAttribute("receiveAccount", receiveAccount);
        session.setAttribute("bankReceiveAccount", bankReceiveAccount);
        model.addAttribute("prepareIT", true);
        model.addAttribute("makeIT", false);
        model.addAttribute("completeIT", false);
        return "/user/internal_transfer";
    }
    
    @RequestMapping(value = "resultPrepareInternalTransfer", method = RequestMethod.POST)
    public String resultPrepareInternalTransfer(Model model) throws MessagingException {
        // get Bank Account Original
        BankAccountEntity bankAccount = bankAccountService.findBankAccountByTypeAndCustomer(AccountType.PAYMENT_ACCOUNT.toString(), 1);
        // enter fields
        String balanceTransferString = request.getParameter("balanceTransfer");
        String balanceTransfer = balanceTransferString.replace(",", "");
        System.out.println(balanceTransfer);
        String captcha = request.getParameter("captcha");
        //check enter captcha and sendmail
        boolean checkCapt = transactionService.checkInternalTransfer(
                model, balanceTransfer, bankAccount, captcha, 
                bankAccount.getCustomer().getCustomerEmail());
        model.addAttribute("balanceTransferString", balanceTransferString);
        session.setAttribute("balanceTransfer", balanceTransfer);
        if (checkCapt == true) {
            return "redirect:/viewConfirmIT";
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
        model.addAttribute("prepareIT", false);
        model.addAttribute("makeIT", true);
        model.addAttribute("completeIT", false);
        return "/user/internal_transfer";
    }

    @RequestMapping(value = "resultMakeInternalTransfer", method = RequestMethod.POST)
    public String resultMakeInternalTransfer(Model model) {
        String confirmCode = request.getParameter("confirmCode");
        boolean checkCCode = transactionService.checkConfirmCode(model, confirmCode);
        if (checkCCode == false) {
            model.addAttribute("makeIT", true);
            model.addAttribute("completeIT", false);
        } else {
            TransactionEntity transaction = new TransactionEntity();
            BankAccountEntity bankAccount = (BankAccountEntity) session.getAttribute("bankAccount");
            transactionService.internalTransfer(transaction, bankAccount.getCustomer().getCustomerEmail());
            model.addAttribute("makeIT", false);
            model.addAttribute("completeIT", true);
            resetSessionTransactionAfter();
        }
        model.addAttribute("prepareIT", false);
        return "/user/internal_transfer";
    }

    

}
