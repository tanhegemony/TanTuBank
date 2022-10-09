/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.repository;

import com.ivt.spring_project_internship_tantubank.entities.BankAccountEntity;
import com.ivt.spring_project_internship_tantubank.enums.AccountType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TanHegemony
 */
@Repository
public interface BankAccountRepository extends CrudRepository<BankAccountEntity, Long>{
    BankAccountEntity findByAccountTypeAndCustomerId(String accountType, long customerId);
    
    BankAccountEntity findByAccountTypeAndAccountNumberAndBankId(String accountType, String accountNumber, long bankId);

}
