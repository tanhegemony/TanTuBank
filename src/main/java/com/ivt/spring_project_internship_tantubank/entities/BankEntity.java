/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author TanHegemony
 */
@Entity
@Table(name = "banks")
public class BankEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "bank_name", length = 50, unique = true)
    private String bankName;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<BankAccountEntity> bankAccounts;

    @Column(name = "image_bank", length = 1000)
    private String imageBank;
    
    @Transient
    private MultipartFile imgBank;

    @OneToMany(mappedBy = "bank",cascade = CascadeType.ALL)
    private List<TanTuBankAddressEntity> bankAddress;

    public List<TanTuBankAddressEntity> getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(List<TanTuBankAddressEntity> bankAddress) {
        this.bankAddress = bankAddress;
    }
    
    public String getImageBank() {
        return imageBank;
    }

    public void setImageBank(String imageBank) {
        this.imageBank = imageBank;
    }

    public MultipartFile getImgBank() {
        return imgBank;
    }

    public void setImgBank(MultipartFile imgBank) {
        this.imgBank = imgBank;
    }
    
    public List<BankAccountEntity> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccountEntity> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
