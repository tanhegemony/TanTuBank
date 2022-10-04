/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.controller;

import com.ivt.spring_project_internship_tantubank.entities.BankAccountEntity;
import com.ivt.spring_project_internship_tantubank.enums.AccountType;
import com.ivt.spring_project_internship_tantubank.service.BankAccountService;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author TanHegemony
 */
@Controller
public class TransferController {
    
    @Autowired
    private BankAccountService bankAccountService;
    
    @Autowired
    HttpServletRequest request;
    
    @Autowired
    HttpSession session;
    
    @RequestMapping("viewInternalTransfer")
    public String viewTransfer(Model model){
        BankAccountEntity bankAccount = bankAccountService.findBankAccountByTypeAndCustomer(AccountType.PAYMENT_ACCOUNT.toString(), 1);
        session.setAttribute("bankAccount", bankAccount);
        model.addAttribute("confirm", false);
        return "/user/internal_transfer";
    }
    
    
    @RequestMapping(value = "resultInternalTransfer", method = RequestMethod.POST)
    public String resultInternalTransfer(Model model){
        String receiveAccount = request.getParameter("receiveAccount");
        BankAccountEntity bankReceiveAccount = bankAccountService.findBankAccountByTypeAndAccountNumber(model, AccountType.PAYMENT_ACCOUNT.toString(), receiveAccount);
        model.addAttribute("bankReceiveAccount", bankReceiveAccount);
        model.addAttribute("receiveAccount", receiveAccount);
        model.addAttribute("confirm", false);
        return "/user/internal_transfer";
    } 
}
