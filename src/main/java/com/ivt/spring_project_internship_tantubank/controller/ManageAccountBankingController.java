/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.controller;

import com.ivt.spring_project_internship_tantubank.entities.BankAccountEntity;
import com.ivt.spring_project_internship_tantubank.enums.AccountStatus;
import com.ivt.spring_project_internship_tantubank.enums.AccountType;
import com.ivt.spring_project_internship_tantubank.model.FilterWithSortBy;
import com.ivt.spring_project_internship_tantubank.service.BankAccountService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author TanHegemony
 */
@Controller
@RequestMapping("/management/")
public class ManageAccountBankingController {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpSession session;

    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = "resultManageBankAccount", method = RequestMethod.POST)
    public String resultManageBankAccount(Model model){
        return "";
    }
    
    @RequestMapping("updateBankAccount/{id}")
    public String editBankAccount(
            @PathVariable("id") long id,
            Model model){
        BankAccountEntity bankAccount = bankAccountService.findBAById(id);
        if(bankAccount != null){
            model.addAttribute("action", "updatePaymentBankAccount");
        }else{
            model.addAttribute("action", "addPaymentBankAccount");
        }
        return "";
    }
    
    @RequestMapping("changeBankAccountStatus/{id}/{accountStatus}")
    public String changeBankAccountStatus(
            @PathVariable("accountStatus") AccountStatus accountStatus,
            @PathVariable("id") long id,
            Model model){
        BankAccountEntity bankAccount = bankAccountService.findBAById(id);
        if(bankAccount != null){
            bankAccountService.changeBankAccountStatus(bankAccount, accountStatus);
        }
        return "redirect:/management/viewManageBankAccount";
    }
    
    @RequestMapping("viewManageBankAccount")
    public String viewManageBankAccount(
            @RequestParam(value = "nav", required = false) String nav,
            @RequestParam(value = "page") Optional<Integer> page,
            @RequestParam(value = "size") Optional<Integer> size,
            Model model) throws ParseException {
        model.addAttribute("action", "viewBankAccounts");
        // set sort
        Sort sort = FilterWithSortBy.resultSortBy("idAscending");
        // define nav
        if (nav.equals("")) {
            nav = "managePaymentBankAccount";
        }
        // pagination
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(1);

        // show bankAccounts
        Page<BankAccountEntity> bankAccounts = null;
        if (nav.equals("managePaymentBankAccount")) {
            bankAccounts = bankAccountService.findBAByTypeAndBank(
                    AccountType.PAYMENT_ACCOUNT.toString(), 1, currentPage - 1, pageSize, sort);
        } else if (nav.equals("manageSavingBankAccount")) {
            bankAccounts = bankAccountService.findBAByTypeAndBank(
                    AccountType.SAVING_ACCOUNT.toString(), 1, currentPage - 1, pageSize, sort);
        } else if (nav.equals("manageExternalBankAccount")) {
            bankAccounts = bankAccountService.findBAByTypeAndNotTanTuBank(
                    AccountType.PAYMENT_ACCOUNT.toString(), 1, currentPage - 1, pageSize, sort);
        }
        if (bankAccounts != null) {
            int totalPage = bankAccounts.getTotalPages();
            model.addAttribute("totalPage", totalPage);
        }
        model.addAttribute("accountStatusList", AccountStatus.values());
        model.addAttribute("nav", nav);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("bankAccounts", bankAccounts);
        session.setAttribute("findAllBankAccounts", bankAccounts);

        // validation date field
        String today = date.format(new Date());
        model.addAttribute("today", today);

        return "/management/manage_account_banking";
    }

    @RequestMapping(value = "searchBankAccount", method = RequestMethod.GET)
    public String searchBankAccount(
            @RequestParam(value = "nav", required = false) String nav,
            @RequestParam(value = "page") Optional<Integer> page,
            @RequestParam(value = "size") Optional<Integer> size,
            @RequestParam(value = "searchValue", required = false) String searchValue,
            Model model) {
        model.addAttribute("action", "searchBankAccount");
        if (nav.equals("")) {
            nav = "managePaymentBankAccount";
        }
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(1);
        Sort sort = FilterWithSortBy.resultSortBy("idAscending");
        Page<BankAccountEntity> bankAccounts =  null;
        if (nav.equals("managePaymentBankAccount")) {
            bankAccounts = bankAccountService.searchBankAccount(AccountType.PAYMENT_ACCOUNT.toString(),
                1, "%" + searchValue + "%", currentPage - 1, pageSize, sort);
        } else if (nav.equals("manageSavingBankAccount")) {
            bankAccounts = bankAccountService.searchBankAccount(AccountType.SAVING_ACCOUNT.toString(),
                1, "%" + searchValue + "%", currentPage - 1, pageSize, sort);
        } else if (nav.equals("manageExternalBankAccount")) {
            bankAccounts = bankAccountService.searchBankAccountNotTanTuBank(AccountType.PAYMENT_ACCOUNT.toString(),
                1, "%" + searchValue + "%", currentPage - 1, pageSize, sort);
        }
        if (bankAccounts != null) {
            int totalPage = bankAccounts.getTotalPages();
            model.addAttribute("totalPage", totalPage);
        }
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("nav", nav);
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("bankAccounts", bankAccounts);
        session.setAttribute("searchBankAccounts", bankAccounts);
        model.addAttribute("accountStatusList", AccountStatus.values());
        String today = date.format(new Date());
        model.addAttribute("today", today);
        return "/management/manage_account_banking";
    }

    @RequestMapping(value = "resultFilter", method = RequestMethod.GET)
    public String resultFilter(
            @RequestParam(value = "nav", required = false) String nav,
            @RequestParam(value = "page") Optional<Integer> page,
            @RequestParam(value = "size") Optional<Integer> size,
            @RequestParam(value = "searchValue", required = false) String searchValue,
            @RequestParam(value = "sortBy") String sortBy,
            Model model) throws ParseException {
        model.addAttribute("action", "filterBankAccount");
        if (nav.equals("")) {
            nav = "managePaymentBankAccount";
        }
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        int totalPage = 0;
        model.addAttribute("nav", nav);
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String accountStatus = request.getParameter("accountStatus");

        boolean resultFilter = bankAccountService.resultFilter(model, nav,
                searchValue,
                startDate, endDate, accountStatus, sortBy,
                currentPage, pageSize, totalPage);
        if (resultFilter == false) {
            return "redirect:/management/viewManageBankAccount";
        }
        String today = date.format(new Date());
        model.addAttribute("today", today);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("accountStatus", accountStatus);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("accountStatusList", AccountStatus.values());
        return "/management/manage_account_banking";
    }

}
