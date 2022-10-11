/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.repository;

import com.ivt.spring_project_internship_tantubank.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author henry
 */
@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long>{
    // find by accountId
    CustomerEntity findByUserId(long userId);
    // find by email
    CustomerEntity findByCustomerEmail(String customerEmail);
    // find by phone
    CustomerEntity findByCustomerPhone(String customerPhone);
     // find by name
    CustomerEntity findByCustomerName(String customerName);
    
}
