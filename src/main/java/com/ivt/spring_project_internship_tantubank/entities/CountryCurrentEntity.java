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
@Table(name = "countryCurrent")
public class CountryCurrentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "name_country", length = 20, unique = true, nullable = false)
    private String nameCountry;
    
    @Column(name = "image_country", length = 200)
    private String imageCountry;
    
    @Transient
    private MultipartFile imgCountry;
    
    @Column(name = "current_value")
    private double currentValue;

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

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }
    
    
}
