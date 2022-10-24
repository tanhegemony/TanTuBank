///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.ivt.spring_project_internship_tantubank.controller;
//
//import com.ivt.spring_project_internship_tantubank.entities.BankAccountEntity;
//import com.ivt.spring_project_internship_tantubank.enums.AccountType;
//import com.ivt.spring_project_internship_tantubank.service.BankAccountService;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// *
// * @author TanHegemony
// */
//@RestController
//@RequestMapping("/api")
//public class WebServiceController {
//    
//    @Autowired
//    BankAccountService bankAccountService;
//    
//    @RequestMapping(value = "/accountsBanking", method = RequestMethod.GET)
//    public ResponseEntity<List<BankAccountEntity>> getAccountBanking(){
//        List<BankAccountEntity> bankAccounts = bankAccountService.findBAByTypeAndBank(AccountType.PAYMENT_ACCOUNT.toString(), 1);
//        return new ResponseEntity<>(bankAccounts, HttpStatus.OK);
//    }
//    
//}
