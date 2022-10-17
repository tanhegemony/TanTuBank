/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.service;

import com.ivt.spring_project_internship_tantubank.entities.CustomerEntity;
import com.ivt.spring_project_internship_tantubank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author henry
 */
@Service
public class CustomerService {
    @Autowired CustomerRepository customerRepository;
    
     // find by email
    public CustomerEntity findCustomerByCustomerEmail(String customerEmail){
        CustomerEntity customer = customerRepository.findByCustomerEmail(customerEmail);
        if(customer != null && customer.getId() > 0){
            return customer;
        }
        return new CustomerEntity();
    }
    // find by phone
    public CustomerEntity findCustomerByCustomerPhone(String customerPhone){
        CustomerEntity customer = customerRepository.findByCustomerPhone(customerPhone);
        if(customer != null && customer.getId() > 0){
            return customer;
        }
        return new CustomerEntity();
    }
    // find by name
    public CustomerEntity findCustomerByCustomerName(String customerName){
        CustomerEntity customer = customerRepository.findByCustomerName(customerName);
        if(customer != null && customer.getId() > 0){
            return customer;
        }
        return new CustomerEntity();
    }
    // find by name and phone
     public CustomerEntity findByCustomerEmailOrCustomerPhone(String emailAndPhone){
        CustomerEntity customer = customerRepository.findByCustomerEmailOrCustomerPhone(emailAndPhone, emailAndPhone);
        if(customer != null && customer.getId() > 0){
            return customer;
        }
        return new CustomerEntity();
    }
    
    
    
     public void saveOrUpdateCustomer(CustomerEntity customer){
        customerRepository.save(customer);
    }
    
}
