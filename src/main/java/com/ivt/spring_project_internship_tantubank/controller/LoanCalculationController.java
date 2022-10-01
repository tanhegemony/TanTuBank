/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.controller;

import com.ivt.spring_project_internship_tantubank.model.LoanCalculation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author TanHegemony
 */
@Controller
public class LoanCalculationController {

    @Autowired
    HttpServletRequest request;

    @RequestMapping("loanCalculation")
    public String loanCalculation(Model model) {
        return "/user/loan_calculation";
    }

    @RequestMapping(value = "resultLoanCalculation", method = RequestMethod.POST)
    public String resultLoanCalculation(Model model) {
        // enter fields
        double loanMoney = Double.parseDouble(request.getParameter("loanMoney"));
        int timeLoan = Integer.parseInt(request.getParameter("timeLoan"));
        double interestRateLoan = Double.parseDouble(request.getParameter("interestRateLoan"));
        
        // calculation interest pay monthly
        double interestPayMonthly = (loanMoney * (interestRateLoan / 100)) / timeLoan;
        
        // show 
        model.addAttribute("loanMoney", loanMoney);
        model.addAttribute("interestRateLoan", interestRateLoan);
        model.addAttribute("interestPayMonthly", new BigDecimal(interestPayMonthly).setScale(
                    0, RoundingMode.HALF_UP)
                    .setScale(0).doubleValue());
        model.addAttribute("timeLoan", timeLoan);
        
        // calculation money pay monthly
        double moneyPayMonthly = loanMoney / timeLoan;
        
        // create new list 
        List<LoanCalculation> loanCalculationList = new ArrayList<>();
        double totalOriginal = 0.0;
        double totalInterest = 0.0;
        double totalOriginalPlusInterest = 0.0;
        for (int i = 0; i < timeLoan; i++) {
            LoanCalculation loanCalculation = new LoanCalculation();
            loanCalculation.setId(i + 1);
            double originalRemain = loanMoney - moneyPayMonthly;
            loanCalculation.setOriginalRemain(
                    new BigDecimal(originalRemain).setScale(
                            0, RoundingMode.HALF_UP)
                            .setScale(0).doubleValue());
            loanCalculation.setOriginal(new BigDecimal(moneyPayMonthly).setScale(
                    0, RoundingMode.HALF_UP)
                    .setScale(0).doubleValue());
            double interest = (loanMoney * (interestRateLoan / 100)) / timeLoan;
            loanCalculation.setInterest(new BigDecimal(interest).setScale(
                    0, RoundingMode.HALF_UP)
                    .setScale(0).doubleValue());
            double originalPlusInterest = moneyPayMonthly + interest;
            loanCalculation.setOriginalPlusInterest(new BigDecimal(originalPlusInterest).setScale(
                    0, RoundingMode.HALF_UP)
                    .setScale(0).doubleValue());
            loanMoney = originalRemain;
            loanCalculationList.add(loanCalculation);
            
            // calculation table total
            totalOriginal += moneyPayMonthly;
            totalInterest += interest;
            totalOriginalPlusInterest += originalPlusInterest;
        }
        double averageOriginalPlusInterestPayMonthly = totalOriginalPlusInterest/timeLoan;
        model.addAttribute("totalOriginal", new BigDecimal(totalOriginal).setScale(
                    0, RoundingMode.HALF_UP)
                    .setScale(0).doubleValue());
        model.addAttribute("totalInterest", new BigDecimal(totalInterest).setScale(
                    0, RoundingMode.HALF_UP)
                    .setScale(0).doubleValue());
        model.addAttribute("totalOriginalPlusInterest", new BigDecimal(totalOriginalPlusInterest).setScale(
                    0, RoundingMode.HALF_UP)
                    .setScale(0).doubleValue());
        model.addAttribute("averageOriginalPlusInterestPayMonthly", new BigDecimal(averageOriginalPlusInterestPayMonthly).setScale(
                    0, RoundingMode.HALF_UP)
                    .setScale(0).doubleValue());
        
        model.addAttribute("loanCalculationList", loanCalculationList);

        return "/user/loan_calculation";
    }
}
