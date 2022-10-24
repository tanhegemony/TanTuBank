/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.controller.user;

import com.ivt.spring_project_internship_tantubank.entities.CountryCurrencyEntity;
import com.ivt.spring_project_internship_tantubank.entities.UserEntity;
import com.ivt.spring_project_internship_tantubank.service.CountryCurrencyService;
import com.ivt.spring_project_internship_tantubank.service.UserService;
import java.text.DecimalFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author TanHegemony
 */
@Controller
public class HomePageController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private CountryCurrencyService countryCurrencyService;

    public UserEntity getUserByUserLogin(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String usernmae = principal.toString();
        if (principal instanceof UserDetails) {
            usernmae = ((UserDetails) principal).getUsername();
        }
        UserEntity user = userService.findByUserName(usernmae);
        model.addAttribute("user", user);
        model.addAttribute("username", usernmae);
        return user;
    }
    
    public void getListCountryCurrency(Model model){
        List<CountryCurrencyEntity> countriesCurrency = countryCurrencyService.getCountryCurrency();
        model.addAttribute("countriesCurrency", countriesCurrency);
    }

    @RequestMapping(value = {"/*", "/home"})
    public String homePage(Model model) {
        getUserByUserLogin(model);
        getListCountryCurrency(model);
        return "/user/home_page";
    }

    @RequestMapping(value = "resultCurrencyConversion", method = RequestMethod.POST)
    public String resultCurrencyConversion(Model model) {
        String balanceConversion = request.getParameter("balanceConversion");
        balanceConversion = balanceConversion.replaceAll(",", "");
        long countryCurrencyId = Long.parseLong(request.getParameter("countryCurrentId"));
        double balanceConversionSoVietNam = countryCurrencyService.currencyConversion(model, balanceConversion, countryCurrencyId);
        model.addAttribute("balanceConversion", balanceConversion);
        model.addAttribute("countryCurrencyId", countryCurrencyId);
        model.addAttribute("balanceConversionSoVietNam", balanceConversionSoVietNam);
        getListCountryCurrency(model);
        return "/user/home_page";
    }

}
