/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.controller.admin;

import com.github.cage.GCage;
import com.ivt.spring_project_internship_tantubank.entities.CustomerEntity;
import com.ivt.spring_project_internship_tantubank.entities.RoleEntity;
import com.ivt.spring_project_internship_tantubank.entities.UserEntity;
import com.ivt.spring_project_internship_tantubank.entities.UserRoleEntity;
import com.ivt.spring_project_internship_tantubank.enums.AccountStatus;
import com.ivt.spring_project_internship_tantubank.service.CustomerService;
import com.ivt.spring_project_internship_tantubank.service.RoleService;
import com.ivt.spring_project_internship_tantubank.service.UserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author henry
 */
@Controller
public class LoginController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpSession session;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    JavaMailSender mailSender;

    // send mail
    public void sendEmail(String from, String to, String subject, String content) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, multipart, "utf-8");
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        mimeMessage.setContent(content, "text/html; charset=utf-8");
        mailSender.send(mimeMessage);
    }

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

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        return "redirect:/home";
    }

    @RequestMapping("/login")
    public String viewLoginPage(Model model,
            @RequestParam(name = "error", required = false) boolean errorLogin) {
        String codeRandomCheck = request.getParameter("captcha");
        if (errorLogin) {
            model.addAttribute("message", "Tài khoản hoặc mật khẩu không đúng!");
        }
        GCage gCage = new GCage();
        String token = gCage.getTokenGenerator().next();
        System.out.println(token);
        System.out.println("image/" + gCage.getFormat());
        session.setAttribute("captcha", token);
        model.addAttribute("captcha", token);
        return "/login";
    }

    @RequestMapping("/logout")
    public String logout(Model model) {
        session.setMaxInactiveInterval(0);
        session.invalidate();
        return "redirect:/home";
    }

    @RequestMapping("/404")
    public String accessDeniedPage(Model model) {
        return "/404";
    }

    @RequestMapping("/view_register")
    public String viewRegiser(Model model) {
        boolean displayCheckAccountBySendMail = false;
        model.addAttribute("displayCheckAccountBySendMail", displayCheckAccountBySendMail);
        GCage gCage = new GCage();
        String token = gCage.getTokenGenerator().next();
        session.setAttribute("captcha", token);
        model.addAttribute("captcha", token);
        return "/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model) throws MessagingException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String passwordAgain = request.getParameter("passwordAgain");
        String customerName = request.getParameter("customerName");
        String customerEmail = request.getParameter("customerEmail");
        String customerPhone = request.getParameter("customerPhone");
        UserEntity userNameEt = userService.findByUserName(userName);
        if (userNameEt.getId() <= 0) {
            boolean add = userService.addRegister(model, 
                    userName, password, customerName, customerEmail, customerPhone, 
                    passwordAgain, "1");
            if (add == true) {
                model.addAttribute("displayCheckAccountBySendMail", true);
                return "/register";
            }
        } else {
            model.addAttribute("message", "Tài khoản đã tồn tại");
            model.addAttribute("displayCheckAccountBySendMail", false);
        }

        return "/register";
    }

    @RequestMapping(value = "checkAccountBySendEmail", method = RequestMethod.POST)
    public String checkAccountBySendMail(Model model) {
        String codeRandomCheck = request.getParameter("codeRandomCheck");
        if (codeRandomCheck.equals(session.getAttribute("randomNumericConfirmRegister"))) {
            UserEntity user = (UserEntity) session.getAttribute("user");
            userService.saveUser(user);
            CustomerEntity customer = (CustomerEntity) session.getAttribute("customer");
            customerService.saveOrUpdateCustomer(customer);
            session.setMaxInactiveInterval(0);
            session.invalidate();
            return "/login";
        } else {
            model.addAttribute("message", "Mã xác nhận đăng ký tài khoản không chính xác!");
            model.addAttribute("displayCheckAccountBySendMail", true);
            return "/register";
        }

    }
}
