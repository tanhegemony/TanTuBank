/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.repository;

import com.ivt.spring_project_internship_tantubank.entities.BankAccountEntity;
import com.ivt.spring_project_internship_tantubank.enums.AccountStatus;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
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

    Page<BankAccountEntity> findByAccountTypeAndBankId(String accountType, long bankId, Pageable pageable);

    
    Page<BankAccountEntity> findByAccountTypeAndBankIdNot(String accountType, long bankId, Pageable pageable);

    @Query("SELECT ba FROM BankAccountEntity ba WHERE ba.accountType = ?1 AND ba.bank.id = ?2 AND (ba.accountName LIKE ?3 OR ba.customer.customerPhone LIKE ?4)")
    Page<BankAccountEntity> searchBankAccountByNameAndPhone(String accountType, long bankId, String accountName, String customerPhone, Pageable pageable);
    
    @Query("SELECT ba FROM BankAccountEntity ba WHERE ba.accountType = ?1 AND ba.bank.id != ?2 AND (ba.accountName LIKE ?3 OR ba.customer.customerPhone LIKE ?4)")
    Page<BankAccountEntity> searchBankAccountByNameAndPhoneNotTanTuBank(String accountType, long bankId, String accountName, String customerPhone, Pageable pageable);

    BankAccountEntity findByCustomerCustomerPhoneAndCustomerCustomerEmailAndAccountTypeAndBankId(String customerPhone, String customerEmail,String accountType, long bankId);

    Page<BankAccountEntity> findByBankIdAndBankAccountStatus(long bankId, AccountStatus accountStatus, Pageable pageable);
}
