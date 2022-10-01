/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.model;

/**
 *
 * @author TanHegemony
 */
public class LoanCalculation {
    private int id;
    private double originalRemain;
    private double original;
    private double interest;
    private double originalPlusInterest;

    public LoanCalculation() {
    }

    public LoanCalculation(int id, double originalRemain, double original, double interest, double originalPlusInterest) {
        this.id = id;
        this.originalRemain = originalRemain;
        this.original = original;
        this.interest = interest;
        this.originalPlusInterest = originalPlusInterest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getOriginalRemain() {
        return originalRemain;
    }

    public void setOriginalRemain(double originalRemain) {
        this.originalRemain = originalRemain;
    }

    public double getOriginal() {
        return original;
    }

    public void setOriginal(double original) {
        this.original = original;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getOriginalPlusInterest() {
        return originalPlusInterest;
    }

    public void setOriginalPlusInterest(double originalPlusInterest) {
        this.originalPlusInterest = originalPlusInterest;
    }

    
    
    
}
