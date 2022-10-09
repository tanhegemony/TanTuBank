/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.service;

import com.ivt.spring_project_internship_tantubank.entities.BankEntity;
import com.ivt.spring_project_internship_tantubank.repository.BankRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TanHegemony
 */
@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;
    
    public List<BankEntity> getBanks(){
        List<BankEntity> banks = (List<BankEntity>) bankRepository.findAll();
        if(banks != null){
            return banks;
        }
        return new ArrayList<>();
    }
    
    public BankEntity findBankById(long bankId){
        Optional<BankEntity> bankOpt = bankRepository.findById(bankId);
        if(bankOpt != null & bankOpt.isPresent()){
            return bankOpt.get();
        }
        return new BankEntity();
    }
}
