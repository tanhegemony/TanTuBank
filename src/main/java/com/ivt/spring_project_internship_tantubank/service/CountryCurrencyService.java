/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.service;

import com.ivt.spring_project_internship_tantubank.entities.CountryCurrencyEntity;
import com.ivt.spring_project_internship_tantubank.repository.CountryCurrencyRepository;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 *
 * @author TanHegemony
 */
@Service
public class CountryCurrencyService {

    @Autowired
    private CountryCurrencyRepository countryCurrencyRepository;

    public List<CountryCurrencyEntity> getCountryCurrency() {
        List<CountryCurrencyEntity> countriesCurrency = (List<CountryCurrencyEntity>) countryCurrencyRepository.findAll();
        if (countriesCurrency != null && countriesCurrency.size() > 0) {
            return countriesCurrency;
        }
        return new ArrayList<>();
    }

    public CountryCurrencyEntity findCountryCurrencyById(long countryCurrencyId){
        Optional<CountryCurrencyEntity> countryCurrencyOpt = countryCurrencyRepository.findById(countryCurrencyId);
        if(countryCurrencyOpt != null && countryCurrencyOpt.isPresent()){
            return countryCurrencyOpt.get();
        }
        return new CountryCurrencyEntity();
    }
    
    public double currencyConversion(Model model, String balanceConversionString, long countryId) {
        if (balanceConversionString.equals("")) {
            model.addAttribute("messageBalanceConversion", "Tiền quy đổi không được để trống");
        } else {
            if(StringUtils.isNumeric(balanceConversionString) == false){
                model.addAttribute("messageBalanceConversion", "Tiền quy đổi phải là số");
            }else{
                double balanceConversion = Double.parseDouble(balanceConversionString);
                CountryCurrencyEntity countryCurrency = findCountryCurrencyById(countryId);
                double balanceVietNam = (balanceConversion * 1)/countryCurrency.getCurrencyValue();
                return balanceVietNam;
            }
        }
        return 0;
    }
}
