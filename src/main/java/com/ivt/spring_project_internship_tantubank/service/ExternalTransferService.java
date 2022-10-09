/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.service;

import com.ivt.spring_project_internship_tantubank.entities.BankAccountEntity;
import com.ivt.spring_project_internship_tantubank.entities.BankEntity;
import com.ivt.spring_project_internship_tantubank.entities.ExternalTransferEntity;
import com.ivt.spring_project_internship_tantubank.entities.TransactionEntity;
import com.ivt.spring_project_internship_tantubank.repository.ExternalTransferRepository;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TanHegemony
 */
@Service
public class ExternalTransferService {
    @Autowired
    private ExternalTransferRepository externalTransferRepository;
    
    @Autowired
    HttpSession session;
    
    public void addExternalTransfer(ExternalTransferEntity externalTransfer, TransactionEntity transation){
        externalTransfer.setBankAccount((BankAccountEntity) session.getAttribute("receiveBankAccount"));
        BankEntity bank = (BankEntity) session.getAttribute("bank");
        externalTransfer.setBankName(bank.getBankName());
        externalTransfer.setTransaction(transation);
        externalTransferRepository.save(externalTransfer);
    }
}
