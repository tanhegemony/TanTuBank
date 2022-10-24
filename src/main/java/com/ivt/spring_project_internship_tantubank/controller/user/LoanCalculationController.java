/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.controller.user;

import com.ivt.spring_project_internship_tantubank.model.LoanCalculation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
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
        String loanMoneyString = request.getParameter("loanMoney");
        loanMoneyString = loanMoneyString.replaceAll(",", "");
        double loanMoney = Double.parseDouble(loanMoneyString);
        int timeLoan = Integer.parseInt(request.getParameter("timeLoan"));
        double interestRateLoan = Double.parseDouble(request.getParameter("interestRateLoan"));

        if (StringUtils.isNumeric(loanMoneyString) == false) {
            model.addAttribute("errorNumber", true);
            model.addAttribute("messageLoanMoney", "Số tiền vay phải là số!");
            model.addAttribute("loanMoneyString", loanMoneyString);
            model.addAttribute("interestRateLoan", interestRateLoan);
            model.addAttribute("timeLoan", timeLoan);
            return "/user/loan_calculation";
        } else {
            // calculation interest pay monthly
            // tiền lãi trả hàng tháng
            double interestPayMonthly = (loanMoney * (interestRateLoan / 100)) / timeLoan;
            
            // show 
            model.addAttribute("interestPayMonthly", Math.round(interestPayMonthly));

            // calculation money pay monthly
            // tính tiền gốc phải trả hàng tháng
            double moneyPayMonthly = loanMoney / timeLoan;

            
            
            // create new list 
            // lưu list
            List<LoanCalculation> loanCalculationList = new ArrayList<>();
            double totalOriginal = 0.0;
            double totalInterest = 0.0;
            double totalOriginalPlusInterest = 0.0;
            for (int i = 0; i < timeLoan; i++) {
                LoanCalculation loanCalculation = new LoanCalculation();
                loanCalculation.setId(i + 1);
                
                // số gốc còn lại
                double originalRemain = loanMoney - moneyPayMonthly;
                loanCalculation.setOriginalRemain(
                        Math.round(originalRemain));
                
                // gốc
                loanCalculation.setOriginal(Math.round(moneyPayMonthly));
                
                // lãi
                double interest = (loanMoney * (interestRateLoan / 100)) / timeLoan;
                loanCalculation.setInterest(Math.round(interest));
                
                //gốc + lãi
                double originalPlusInterest = moneyPayMonthly + interest;
                loanCalculation.setOriginalPlusInterest(Math.round(originalPlusInterest));
               
                // gán tiền vay cho tiền gốc đã trả
                loanMoney = originalRemain;
                loanCalculationList.add(loanCalculation);

                // calculation table total
                totalOriginal += moneyPayMonthly;
                totalInterest += interest;
                totalOriginalPlusInterest += originalPlusInterest;
            }
            // trung bình gốc + lãi phải trả hàng tháng
            double averageOriginalPlusInterestPayMonthly = totalOriginalPlusInterest / timeLoan;
            
            
            model.addAttribute("totalOriginal", Math.round(totalOriginal));
            model.addAttribute("totalInterest", Math.round(totalInterest));
            model.addAttribute("totalOriginalPlusInterest", Math.round(totalOriginalPlusInterest));
            model.addAttribute("averageOriginalPlusInterestPayMonthly", Math.round(averageOriginalPlusInterestPayMonthly));

            model.addAttribute("loanCalculationList", loanCalculationList);
        }
        model.addAttribute("loanMoneyString", loanMoneyString);
        model.addAttribute("interestRateLoan", interestRateLoan);
        model.addAttribute("timeLoan", timeLoan);
        return "/user/loan_calculation";
    }
}
