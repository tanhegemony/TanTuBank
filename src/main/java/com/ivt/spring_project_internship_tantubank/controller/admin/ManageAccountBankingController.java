/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.controller.admin;

import com.ivt.spring_project_internship_tantubank.entities.BankAccountEntity;
import com.ivt.spring_project_internship_tantubank.entities.StaffEntity;
import com.ivt.spring_project_internship_tantubank.entities.TanTuBankAddressEntity;
import com.ivt.spring_project_internship_tantubank.enums.AccountStatus;
import com.ivt.spring_project_internship_tantubank.enums.AccountType;
import com.ivt.spring_project_internship_tantubank.model.FilterWithSortBy;
import com.ivt.spring_project_internship_tantubank.service.BankAccountService;
import com.ivt.spring_project_internship_tantubank.service.StaffService;
import com.ivt.spring_project_internship_tantubank.service.TanTuBankAddressService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private TanTuBankAddressService tanTuBankAddressService;

    @Autowired
    private StaffService staffService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpSession session;

    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping("viewManageBankAccount")
    public String viewManageBankAccount(
            @RequestParam(value = "nav", required = false) String nav,
            @RequestParam(value = "page") Optional<Integer> page,
            @RequestParam(value = "size") Optional<Integer> size,
            @RequestParam(value = "sortBy", defaultValue = "createDateDescending") String sortBy,
            Model model) throws ParseException {
        model.addAttribute("action", "viewBankAccounts");
        // set sort
        Sort sort = FilterWithSortBy.resultSortBy("createDateDescending");
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
        } else if (nav.equals("manageUnActiveBankAccount")) {
            bankAccounts = bankAccountService.findBAByBankAndStatus(
                    1, AccountStatus.UNACTIVE, currentPage - 1, pageSize, sort);
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
        model.addAttribute("sortBy", sortBy);
        return "/management/manage_account_banking";
    }

//    Mở tài khoản tại ngân hàng: 
//    Bước 1: Đăng ký tài khoản (user và customer), tài khoản sẽ đc lưu vào database với status ACTIVE, 
//    Bước 2: Bắt đầu mở tài khoản
    @RequestMapping("viewOpenOrEditBankAccount")
    public String viewOpenOrEditBankAccount(Model model) {
        model.addAttribute("action", "openBankAccount");
        model.addAttribute("makeOpenBA", true);
        model.addAttribute("physicalCard", false);
        List<TanTuBankAddressEntity> tantubankAddressList = tanTuBankAddressService.getTanTuBankAddressList();
        model.addAttribute("tantubankAddressList", tantubankAddressList);
        model.addAttribute("accountTypes", AccountType.values());
        session.setAttribute("tantuBankAddress", new String());
        return "/management/open_or_edit_bank_account_management";
    }

    @RequestMapping(value = "resultManageBankAccount", method = RequestMethod.POST)
    public String resultManageBankAccount(
            @Valid @ModelAttribute("bankAccount") BankAccountEntity bankAccount,
            BindingResult result,
            Model model) throws MessagingException {
        if (result.hasErrors()) {
            if (bankAccount.getId() > 0) {
                model.addAttribute("action", "editBankAccount");
            } else {
                model.addAttribute("action", "openBankAccount");
            }
            model.addAttribute("makeOpenBA", true);
        } else {
            
            if (bankAccount.getId() <= 0) {
                model.addAttribute("action", "openBankAccount");
                String customerPhone = request.getParameter("customerPhone");
                String customerEmail = request.getParameter("customerEmail");
                String tantuBankAddress = request.getParameter("tantuBankAddress");
                String buttonOpen = request.getParameter("buttonOpenBA");
                
                boolean physicalCard = Boolean.parseBoolean(request.getParameter("physicalCard"));
                String staffId = request.getParameter("staffId");
                session.setAttribute("tantuBankAddress", tantuBankAddress);
                model.addAttribute("customerPhone", customerPhone);
                model.addAttribute("customerEmail", customerEmail);
                model.addAttribute("physicalCard", physicalCard);
                model.addAttribute("validationDate", date.format(new Date()));
                model.addAttribute("staffId", staffId);
                
                boolean makeOpen = bankAccountService.openBankAccount(model, bankAccount,
                        customerPhone, customerEmail, staffId, physicalCard, tantuBankAddress,buttonOpen);
                if (makeOpen == true) {
                    model.addAttribute("makeOpenBA", false);
                    model.addAttribute("bankAccount", bankAccount);
                    return "/management/open_or_edit_bank_account_management";
                } else {
                    model.addAttribute("makeOpenBA", true);
                }
            } else {
                String editStaffId = request.getParameter("editStaffId");
                String editTantuBankAddress = request.getParameter("tantuBankAddress");
                String buttonEdit = request.getParameter("buttonEditBA");
                boolean physicalCard = Boolean.parseBoolean(request.getParameter("physicalCard"));
               
                BankAccountEntity bankAcc = bankAccountService.findBAById(bankAccount.getId());
                if(bankAcc.isPhysicalCard() != physicalCard){
                    bankAcc.setPhysicalCard(physicalCard);
                }
                boolean editBankAccount = bankAccountService.editBankAccount(
                        model, bankAcc, physicalCard, editStaffId, editTantuBankAddress,buttonEdit);
                
                if(editBankAccount == true){
                    return "redirect:/management/editBankAccount/" + bankAccount.getId();
                }
                
                model.addAttribute("editStaffId", editStaffId);
                model.addAttribute("editTantuBankAddress", editTantuBankAddress);
                model.addAttribute("findBankAccount", bankAcc);
                model.addAttribute("makeOpenBA", true);
                model.addAttribute("action", "editBankAccount");
                model.addAttribute("physicalCard", physicalCard);

            }
            

        }
        if (bankAccount.getId() > 0) {
            model.addAttribute("action", "editBankAccount");
        } else {
            model.addAttribute("action", "openBankAccount");
        }
        model.addAttribute("bankAccount", bankAccount);

        List<TanTuBankAddressEntity> tantubankAddressList = tanTuBankAddressService.getTanTuBankAddressList();
        model.addAttribute("tantubankAddressList", tantubankAddressList);
        model.addAttribute("accountTypes", AccountType.values());
        return "/management/open_or_edit_bank_account_management";
    }

    @RequestMapping("editBankAccount/{id}")
    public String editBankAccount(
            @PathVariable("id") long id,
            Model model) {
        BankAccountEntity findBankAccount = bankAccountService.findBAById(id);
        if (findBankAccount != null) {
            model.addAttribute("action", "editBankAccount");
        } else {
            model.addAttribute("action", "openBankAccount");
        }
        model.addAttribute("physicalCard", findBankAccount.isPhysicalCard());
        model.addAttribute("findBankAccount", findBankAccount);
        model.addAttribute("makeOpenBA", true);
        List<TanTuBankAddressEntity> tantubankAddressList = tanTuBankAddressService.getTanTuBankAddressList();
        model.addAttribute("tantubankAddressList", tantubankAddressList);
        model.addAttribute("accountTypes", AccountType.values());
        return "/management/open_or_edit_bank_account_management";
    }

    @RequestMapping("deleteBankAccount/{action}/{nav}/{id}")
    public String deleteBankAccount(
            @PathVariable("action") String action,
            @PathVariable("nav") String nav,
            @PathVariable("id") long id,
            Model model) {
        if (id > 0) {
            bankAccountService.deleteBankAccount(id);
        }
        return "redirect:/management/viewManageBankAccount?action=" + action + "&nav=" + nav;

    }

    // change status
    @RequestMapping("changeBankAccountStatus/{action}/{nav}/{currentPage}/{pageSize}/{id}/{accountStatus}")
    public String changeBankAccountStatus(
            @PathVariable("action") String action,
            @PathVariable("nav") String nav,
            @PathVariable("currentPage") int currentPage,
            @PathVariable("pageSize") int pageSize,
            @PathVariable("id") long id,
            @PathVariable("accountStatus") String accountStatus,
            Model model) {
        BankAccountEntity bankAccount = bankAccountService.findBAById(id);
        if (bankAccount != null) {
            bankAccountService.changeBankAccountStatus(bankAccount, AccountStatus.valueOf(accountStatus));
        }
        return "redirect:/management/viewManageBankAccount?action=" + action + "&nav=" + nav + "&page=" + currentPage + "&size=" + pageSize;
    }

    @RequestMapping(value = "searchBankAccount", method = RequestMethod.GET)
    public String searchBankAccount(
            @RequestParam(value = "nav", required = false) String nav,
            @RequestParam(value = "page") Optional<Integer> page,
            @RequestParam(value = "size") Optional<Integer> size,
            @RequestParam(value = "sortBy", defaultValue = "createDateDescending") String sortBy,
            @RequestParam(value = "searchValue", required = false) String searchValue,
            Model model) {
        model.addAttribute("action", "searchBankAccount");
        if (nav.equals("")) {
            nav = "managePaymentBankAccount";
        }
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(1);
        Sort sort = FilterWithSortBy.resultSortBy("createDateDescending");
        Page<BankAccountEntity> bankAccounts = null;
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
        model.addAttribute("sortBy", sortBy);
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
