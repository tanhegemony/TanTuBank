/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.entities;

import com.ivt.spring_project_internship_tantubank.enums.TransactionType;
import java.sql.Timestamp;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author TanHegemony
 */
@Entity
@Table(name = "transaction")
public class TransactionEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "transaction_type", length = 50)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    
    @Column(name = "transaction_amount")
    private double transactionAmount;
    
    @Column(name = "transaction_content", length = 100)
    private String transactionContent;
    
    @Column(name = "transaction_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp transactionDate;
    
    @ManyToOne
    @JoinColumn(name = "bankAccountId1")
    private BankAccountEntity bankAccount1;
    
    @ManyToOne
    @JoinColumn(name = "bankAccountId2")
    private BankAccountEntity bankAccount2;
    
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<ExternalTransferEntity> externalTransfers;

    @Column(name = "tantubank_address", length = 100)
    private String tantuBankAddress;
    
    @ManyToOne
    @JoinColumn(name = "staffId")
    private StaffEntity staff; 
    
    @Column(name = "fee_transaction")
    private double feeTransaction;

    public String getTantuBankAddress() {
        return tantuBankAddress;
    }

    public void setTantuBankAddress(String tantuBankAddress) {
        this.tantuBankAddress = tantuBankAddress;
    }
    
    public StaffEntity getStaff() {
        return staff;
    }

    public void setStaff(StaffEntity staff) {
        this.staff = staff;
    }

    public double getFeeTransaction() {
        return feeTransaction;
    }

    public void setFeeTransaction(double feeTransaction) {
        this.feeTransaction = feeTransaction;
    }
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTransactionContent() {
        return transactionContent;
    }

    public void setTransactionContent(String transactionContent) {
        this.transactionContent = transactionContent;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BankAccountEntity getBankAccount1() {
        return bankAccount1;
    }

    public void setBankAccount1(BankAccountEntity bankAccount1) {
        this.bankAccount1 = bankAccount1;
    }

    public BankAccountEntity getBankAccount2() {
        return bankAccount2;
    }

    public void setBankAccount2(BankAccountEntity bankAccount2) {
        this.bankAccount2 = bankAccount2;
    }

    public List<ExternalTransferEntity> getExternalTransfers() {
        return externalTransfers;
    }

    public void setExternalTransfers(List<ExternalTransferEntity> externalTransfers) {
        this.externalTransfers = externalTransfers;
    }
    
    
}
