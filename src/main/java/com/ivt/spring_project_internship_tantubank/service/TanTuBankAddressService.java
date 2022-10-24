/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.service;

import com.ivt.spring_project_internship_tantubank.entities.TanTuBankAddressEntity;
import com.ivt.spring_project_internship_tantubank.repository.TanTuBankAddresssRespository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TanHegemony
 */
@Service
public class TanTuBankAddressService {
    @Autowired
    private TanTuBankAddresssRespository tanTuBankAddresssRespository;
    
    public List<TanTuBankAddressEntity> getTanTuBankAddressList(){
        List<TanTuBankAddressEntity> tantubankAddressList = (List<TanTuBankAddressEntity>) tanTuBankAddresssRespository.findAll();
        if(tantubankAddressList != null && tantubankAddressList.size() > 0){
            return tantubankAddressList;
        }
        return new ArrayList<>();
    }
}
