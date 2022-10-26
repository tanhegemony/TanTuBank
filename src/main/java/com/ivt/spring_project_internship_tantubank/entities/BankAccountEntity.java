/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.entities;

import com.ivt.spring_project_internship_tantubank.enums.AccountStatus;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

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
    
    @Column(name = "account_name", length = 50)
    private String accountName;
    
    private double balance;
    
    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createDate;
    
    @Column(name = "account_type", length = 20)
    private String accountType;
    
    @Column(name = "physical_card")
    private boolean physicalCard = false;
    
    @Column(name = "bank_account_status", length = 10)
    @Enumerated(EnumType.STRING)
    private AccountStatus bankAccountStatus;

    @ManyToOne
    @JoinColumn(name = "staffId")
    private StaffEntity staff;
    
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

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    private List<ExternalTransferEntity> externalTransfers;

    public boolean isPhysicalCard() {
        return physicalCard;
    }

    public void setPhysicalCard(boolean physicalCard) {
        this.physicalCard = physicalCard;
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

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public AccountStatus getBankAccountStatus() {
        return bankAccountStatus;
    }

    public void setBankAccountStatus(AccountStatus bankAccountStatus) {
        this.bankAccountStatus = bankAccountStatus;
    }

    
    public StaffEntity getStaff() {
        return staff;
    }

    public void setStaff(StaffEntity staff) {
        this.staff = staff;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
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

    public List<ExternalTransferEntity> getExternalTransfers() {
        return externalTransfers;
    }

    public void setExternalTransfers(List<ExternalTransferEntity> externalTransfers) {
        this.externalTransfers = externalTransfers;
    }


    
    
    
}
