/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.service;

import com.ivt.spring_project_internship_tantubank.entities.CustomerEntity;
import com.ivt.spring_project_internship_tantubank.entities.RoleEntity;
import com.ivt.spring_project_internship_tantubank.entities.UserEntity;
import com.ivt.spring_project_internship_tantubank.entities.UserRoleEntity;
import com.ivt.spring_project_internship_tantubank.enums.UserStatus;
import com.ivt.spring_project_internship_tantubank.repository.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 *
 * @author henry
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    HttpSession session;

    @Autowired
    JavaMailSender mailSender;

    public void sendMail(String from, String to, String subject, String content) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, multipart, "utf-8");
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        mimeMessage.setContent(content, "text/html; charset=utf-8");

        mailSender.send(mimeMessage);
    }

    public UserEntity findByUserName(String userName) {
        UserEntity user = userRepository.findByUserName(userName);
        if (user != null && user.getId() > 0) {
            return user;
        }
        return new UserEntity();
    }

    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    public boolean addRegister(Model model, String userName, String password,
            String customerName, String customerEmail,
            String customerPhone, String passwordAgain, String captcha) throws MessagingException {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        UserEntity user = new UserEntity();
        user.setUserName(userName);
        user.setPassword(encoder.encode(password));
        user.setCreateDate(new Date());
        user.setUserStatus(UserStatus.ACTIVE);
        
        List<UserRoleEntity> roleList = new ArrayList<>();
        UserRoleEntity roleA = new UserRoleEntity();
        List<RoleEntity> roles = roleService.getRoles();
        roleA.setRole(roles.get(0));
        roleA.setUser(user);
        System.out.println(roles.get(0));
        System.out.println(user);
        roleList.add(roleA);
        user.setUserRoles(roleList);
        System.out.println(roleList);
        if (!captcha.equals(session.getAttribute("captcha"))) {
            model.addAttribute("message", "Captcha kh??ng ch??nh x??c");
            String reloadCaptcha = RandomStringUtils.randomAlphanumeric(6);
            session.setAttribute("captcha", reloadCaptcha);
            model.addAttribute("displayCheckAccountBySendMail", false);
        } else {
            String pattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&-+=]).{6,}$";
            if (password.matches(pattern) == false) {
                model.addAttribute("message", "M???t kh???u sai ?????nh d???ng!! Vui l??ng ki???m tra l???i!!");
                model.addAttribute("displayCheckAccountBySendMail", false);
            } else {
                if (password.equals(passwordAgain) == false) {
                    model.addAttribute("message", "Nh???p l???i m???t kh???u kh??ng tr??ng");
                    model.addAttribute("displayCheckAccountBySendMail", false);
                } else {
                    CustomerEntity customerCustomerEmail = customerService.findCustomerByCustomerEmail(customerEmail);
                    if (customerCustomerEmail.getId() > 0) {
                        model.addAttribute("message", "S??? ??i???n tho???i n??y ???? ???????c s??? d???ng!!");
                        model.addAttribute("displayCheckAccountBySendMail", false);
                    } else {
                        CustomerEntity customerCustomerPhone = customerService.findCustomerByCustomerPhone(customerPhone);
                        if (customerCustomerPhone.getId() > 0) {
                            model.addAttribute("message", "S??? ??i???n tho???i n??y ???? ???????c s??? d???ng!!");
                            model.addAttribute("displayCheckAccountBySendMail", false);
                        } else {
                            if (customerPhone.length() < 10 || customerPhone.length() > 12) {
                                model.addAttribute("message", "S??? ??i???n tho???i n??y kh??ng ????ng ?????nh d???ng!!");
                                model.addAttribute("displayCheckAccountBySendMail", false);
                            } else {
                                CustomerEntity customerCustomerName = customerService.findCustomerByCustomerName(customerName);
                                if (customerCustomerName.getId() < 0) {
                                    model.addAttribute("message", "T??n ng?????i d??ng ???? t???n t???i");
                                    model.addAttribute("displayCheckAccountBySendMail", false);
                                } else {
                                    session.setAttribute("user", user);
                                    CustomerEntity customer = new CustomerEntity();
                                    customer.setCustomerName(customerName);
                                    customer.setCustomerEmail(customerEmail);
                                    customer.setCustomerPhone(customerPhone);
                                    customer.setUser(user);
                                    session.setAttribute("customer", customer);
                                    String randomNumeric = RandomStringUtils.randomNumeric(6);
                                    String subject = "X??c nh???n th??ng tin ????ng k?? t??i kho???n t???i TanTuBank";
                                    String content = "<h1>B???n vui l??ng nh???p m?? s??? g???m 6 k?? t??? v??o khung x??c nh???n ????ng k??!</h1>"
                                            + "<h3 style='text-align:center'>M?? g???m 6 k?? t??? s???: <b>" + randomNumeric + "</b></h3>";
                                    sendMail("natsutan94@gmail.com", customerEmail, subject, content);
                                    session.setAttribute("randomNumericConfirmRegister", randomNumeric);
                                    session.setMaxInactiveInterval(60);
                                    model.addAttribute("Email", customerEmail);
                                    return true;
                                }

                            }
                        }

                    }
                }
            }
        }
        return false;
    }
}
