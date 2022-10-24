/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.model;

import org.springframework.data.domain.Sort;

/**
 *
 * @author TanHegemony
 */
public class FilterWithSortBy {
     public static Sort resultSortBy(String sortBy){
        Sort sort = null;
        switch (sortBy) {
            case "":
                sortBy = "createDateDescending";
            case "idAscending":
                sort = Sort.by("id").ascending();
                break;
            case "idDescending":
                sort = Sort.by("id").descending();
                break;
            case "accountNumberAscending":
                sort = Sort.by("accountNumber").ascending();
                break;
            case "accountNumberDescending":
                sort = Sort.by("accountNumber").descending();
                break;
            case "accountNameAscending":
                sort = Sort.by("accountName").ascending();
                break;
            case "accountNameDescending":
                sort = Sort.by("accountName").descending();
                break;
            case "createDateAscending":
                sort = Sort.by("createDate").ascending();
                break;
            case "createDateDescending":
                sort = Sort.by("createDate").descending();
                break;
            case "customerIdAscending":
                sort = Sort.by("customer.id").ascending();
                break;
            case "customerIdDescending":
                sort = Sort.by("customer.id").descending();
                break;
        }
        return sort; 
    }
}
