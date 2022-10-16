/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author TanHegemony
 */
@Entity
@Table(name = "countryCurrency")
public class CountryCurrencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "name_country", length = 20, unique = true, nullable = false)
    private String nameCountry;
    
    @Column(name = "image_country", length = 200)
    private String imageCountry;
    
    @Transient
    private MultipartFile imgCountry;
    
    @Column(name = "currency_value")
    private double currencyValue;

    @Column(name = "currency_unit", length = 10)
    private String currencyUnit;
    
    @Column(name = "currency_type", length = 20)
    private String currencyType;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public String getImageCountry() {
        return imageCountry;
    }

    public void setImageCountry(String imageCountry) {
        this.imageCountry = imageCountry;
    }

    public MultipartFile getImgCountry() {
        return imgCountry;
    }

    public void setImgCountry(MultipartFile imgCountry) {
        this.imgCountry = imgCountry;
    }

    public double getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(double currencyValue) {
        this.currencyValue = currencyValue;
    }

    public String getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }
}
