/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author TanHegemony
 */
@Entity
@Table(name = "bankAccount")
public class BankAccountEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "account_number", length = 20, unique = true)
    private String accountNumber;
    
    @Column(name = "account_name", length = 50, unique = true)
    private String accountName;
    
    private double balance;
    
    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    
    @Column(name = "account_type", length = 20)
    private String accountType;
    
    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "bankId")
    private BankEntity bank;
    
    @OneToMany(mappedBy = "bankAccount1", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions1;
    
    @OneToMany(mappedBy = "bankAccount2", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions2;

    @OneToOne(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    private ExternalTransferEntity externalTransfer;

    public ExternalTransferEntity getExternalTransfer() {
        return externalTransfer;
    }

    public void setExternalTransfer(ExternalTransferEntity externalTransfer) {
        this.externalTransfer = externalTransfer;
    }
    
    public BankEntity getBank() {
        return bank;
    }

    public void setBank(BankEntity bank) {
        this.bank = bank;
    }

    public List<TransactionEntity> getTransactions1() {
        return transactions1;
    }

    public void setTransactions1(List<TransactionEntity> transactions1) {
        this.transactions1 = transactions1;
    }

    public List<TransactionEntity> getTransactions2() {
        return transactions2;
    }

    public void setTransactions2(List<TransactionEntity> transactions2) {
        this.transactions2 = transactions2;
    }
    
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
    
    
}
