/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.service;

import com.ivt.spring_project_internship_tantubank.entities.StaffEntity;
import com.ivt.spring_project_internship_tantubank.repository.StaffRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 *
 * @author TanHegemony
 */
@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public StaffEntity findStaffById(Model model, long staffId) {
        if (staffId == 0) {
            model.addAttribute("messageStaff", "Staff không được để trống");
        } else {
            Optional<StaffEntity> staffOpt = staffRepository.findById(staffId);
            if (staffOpt != null && staffOpt.isPresent()) {
                return staffOpt.get();
            }else{
                model.addAttribute("messageStaff", "Staff không tồn tại!");
            }
        }
        
        return new StaffEntity();

    }
}
