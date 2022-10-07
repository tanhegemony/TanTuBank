/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.entities;

import com.ivt.spring_project_internship_tantubank.enums.TransactionType;
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
    
    @Column(name = "transaction_date")
    @Temporal(TemporalType.DATE)
    private Date transactionDate;
    
    @ManyToOne
    @JoinColumn(name = "bankAccountId1")
    private BankAccountEntity bankAccount1;
    
    @ManyToOne
    @JoinColumn(name = "bankAccountId2")
    private BankAccountEntity bankAccount2;
    
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<ExternalTransferEntity> externalTransfers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
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
