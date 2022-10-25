/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.controller.user;

import com.ivt.spring_project_internship_tantubank.entities.BankAccountEntity;
import com.ivt.spring_project_internship_tantubank.entities.BankEntity;
import com.ivt.spring_project_internship_tantubank.entities.TransactionEntity;
import com.ivt.spring_project_internship_tantubank.enums.AccountType;
import com.ivt.spring_project_internship_tantubank.enums.TransactionType;
import com.ivt.spring_project_internship_tantubank.service.BankAccountService;
import com.ivt.spring_project_internship_tantubank.service.BankService;
import com.ivt.spring_project_internship_tantubank.service.TransactionService;
import java.util.List;
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
public class ExternalTransferController {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BankService bankService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpSession session;

    public void resetSessionTransactionAfter() {
        session.setAttribute("receiveAccount", new String());
        session.setAttribute("bankReceiveAccount", new BankAccountEntity());
        session.setAttribute("balanceTransaction", new String());
        session.setAttribute("captcha", new String());
        session.setAttribute("confirmCode", new String());
        session.setAttribute("bank", new BankEntity());
        session.setAttribute("contentTransaction", new String());
    }

    @RequestMapping("viewExternalTransfer")
    public String viewExternalTransfer(Model model) {
        model.addAttribute("action", "external_transfer");
        BankAccountEntity bankAccount = bankAccountService.
                findBankAccountByTypeAndCustomer(
                        AccountType.PAYMENT_ACCOUNT.toString(), 1);
        session.setAttribute("bankAccount", bankAccount);

        String captcha = RandomStringUtils.randomAlphanumeric(6);
        session.setAttribute("captcha", captcha);

        List<BankEntity> banks = bankService.getBanks();
        model.addAttribute("banks", banks);

        session.setAttribute("receiveAccount", new String());
        session.setAttribute("bankReceiveAccount", new BankAccountEntity());
        session.setAttribute("balanceTransaction", new String());
        session.setAttribute("contentTransaction", new String());
        session.setAttribute("bankId", 0);
        model.addAttribute("prepareET", true);
        model.addAttribute("makeET", false);
        model.addAttribute("completeET", false);
        return "/user/external_transfer";
    }

    @RequestMapping(value = "resultPrepareExternalTransfer", method = RequestMethod.POST)
    public String resultPrepareExternalTransfer(Model model) throws MessagingException {
        model.addAttribute("action", "external_transfer");
        String receiveAccount = request.getParameter("receiveAccount");
        String bankIdString = request.getParameter("bankId");
        if (bankIdString.equals("")) {
            model.addAttribute("messageBank", "Bạn vui lòng chọn Bank!");
        } else {
            long bankId = Long.parseLong(bankIdString);
            session.setAttribute("bankId", bankId);
            if (receiveAccount.equals("")) {
                model.addAttribute("messageReceiveAccountNumber", "Bạn chưa nhập tài khoản nhận!");
            } else {
                BankAccountEntity receiveBankAccount = bankAccountService.
                        findBankAccountByTypeAndAccountNumberAndBank(model,
                                AccountType.PAYMENT_ACCOUNT.toString(),
                                receiveAccount, bankId);
                if (receiveBankAccount != null) {
                    BankAccountEntity bankAccount = (BankAccountEntity) session.getAttribute("bankAccount");
                    String balanceTransactionString = request.getParameter("balanceTransfer");
                    String contentTransaction = request.getParameter("contentTransfer");
                    String captcha = request.getParameter("captcha");
                    boolean checkTrans = transactionService.checkTransaction(
                            model, "external_transfer", contentTransaction, balanceTransactionString,
                            bankAccount, captcha, bankAccount.getCustomer().getCustomerEmail());
                    session.setAttribute("balanceTransaction", balanceTransactionString);
                    session.setAttribute("contentTransaction", contentTransaction);
                    session.setAttribute("receiveBankAccount", receiveBankAccount);
                    if (checkTrans == true) {
                        BankEntity bank = bankService.findBankById((Long) session.getAttribute("bankId"));
                        session.setAttribute("bank", bank);
                        model.addAttribute("prepareET", false);
                        model.addAttribute("makeET", true);
                        model.addAttribute("completeET", false);
                        return "/user/external_transfer";
                    }
                }
            }
        }
        model.addAttribute("receiveAccount", receiveAccount);
        List<BankEntity> banks = bankService.getBanks();
        model.addAttribute("banks", banks);
        model.addAttribute("prepareET", true);
        model.addAttribute("makeET", false);
        model.addAttribute("completeET", false);
        return "/user/external_transfer";
    }

    //change captcha with AJAX
    @RequestMapping(value = "changeCaptET", method = RequestMethod.GET)
    public @ResponseBody
    String ajaxChangeCaptchaET(Model model) {
        String reloadCaptcha = RandomStringUtils.randomAlphanumeric(6);
        session.setAttribute("captcha", reloadCaptcha);
        return reloadCaptcha;
    }

    @RequestMapping(value = "resultMakeExternalTransfer", method = RequestMethod.POST)
    public String resultMakeExternalTransfer(Model model) {
        model.addAttribute("action", "external_transfer");
        if(session.getAttribute("receiveBankAccount") == null){
            return "redirect:/viewExternalTransfer";
        }
        String confirmCode = request.getParameter("confirmCode");
        boolean checkConfirmCode = transactionService.checkConfirmCode(model, confirmCode);
        if (checkConfirmCode == false) {
            model.addAttribute("makeET", true);
            model.addAttribute("completeET", false);
        } else {
            TransactionEntity transaction = new TransactionEntity();
            transaction.setTransactionType(TransactionType.EXTERNAL_TRANSFER_PAYMENT_ACCOUNT);
            transactionService.makeTransfer("external_transfer", transaction);
            model.addAttribute("makeET", false);
            model.addAttribute("completeET", true);
            resetSessionTransactionAfter();
        }
        model.addAttribute("prepareET", false);
        return "/user/external_transfer";
    }
}
