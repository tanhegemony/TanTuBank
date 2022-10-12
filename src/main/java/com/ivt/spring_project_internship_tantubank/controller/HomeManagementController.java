/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author henry
 */
@Controller
@RequestMapping("/management/")
public class HomeManagementController {
    @RequestMapping("dashboard")
    public String viewDashboard(Model model){
        return "/management/home";
    }
}
