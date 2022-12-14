/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.service;

import com.ivt.spring_project_internship_tantubank.entities.CustomerEntity;
import com.ivt.spring_project_internship_tantubank.entities.UserEntity;
import com.ivt.spring_project_internship_tantubank.repository.CustomerRepository;
import com.ivt.spring_project_internship_tantubank.repository.UserRepository;
import com.sun.mail.util.MessageRemovedIOException;
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
public class ChangePasswordService {

    @Autowired
    private UserService userService;

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

    public boolean checkEmailPhoneAndUserName(Model model, String userName, String emailAndPhone) throws MessagingException {

        CustomerEntity customerCustomerEmailAndPhone = customerService.findByCustomerEmailOrCustomerPhone(emailAndPhone);
        if (customerCustomerEmailAndPhone.getId() <= 0) {
            model.addAttribute("message", "Email ho???c s??? ??i???n tho???i kh??ng t??ng t???i");
        } else {
            UserEntity user = userService.findByUserName(userName);
            if (user.getId() <= 0) {
                model.addAttribute("message", "T??i kho???ng kh??ng t???n t???i");
            } else {
                session.setAttribute("user", user);
                session.setAttribute("customer", customerCustomerEmailAndPhone);
                String randomNumeric = RandomStringUtils.randomNumeric(6);
                String subject = "X??c nh???n th??ng tin TanTuBank";
                String content = "<h1>B???n vui l??ng nh???p m?? s??? g???m 6 k?? t??? v??o khung x??c nh???n ?????i m???t kh???u!</h1>"
                        + "<h3 style='text-align:center'>M?? g???m 6 k?? t??? s???: <b>" + randomNumeric + "</b></h3>";
                sendMail("natsutan94@gmail.com", customerCustomerEmailAndPhone.getCustomerEmail(), subject, content);
                session.setAttribute("randomNumericConfirmRegister", randomNumeric);
                session.setMaxInactiveInterval(60);
                model.addAttribute("Email", customerCustomerEmailAndPhone.getCustomerEmail());
                return true;
            }

        }

        return false;
    }

    public boolean seveChengPass(Model model, String password, String resetPassword) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        UserEntity  user =  (UserEntity) session.getAttribute("user");
        
        String pattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&-+=]).{6,}$";
        if (password.matches(pattern) == false) {
            model.addAttribute("message", "M???t kh???u sai ?????nh d???ng!! Vui l??ng ki???m tra l???i!!");
        } else {
            if (password.equals(resetPassword) == false) {
                model.addAttribute("message", "M???t kh???u kh??ng tr??ng");
            } else {
                System.out.println("pass :" + user.getPassword());
                if (encoder.matches(password,user.getPassword())) {
                    model.addAttribute("message", "Kh??ng th??? s??? d???ng m???t kh???u c??");
                } else {
                    if (user.getId() > 0) {
                        user.setPassword(encoder.encode(password));
                        userService.saveUser(user);
                        return true;
                    }

                }
            }
        }
        return false;
    }

}
