/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.service;

import com.ivt.spring_project_internship_tantubank.entities.BankAccountEntity;
import com.ivt.spring_project_internship_tantubank.entities.TransactionEntity;
import com.ivt.spring_project_internship_tantubank.enums.TransactionType;
import com.ivt.spring_project_internship_tantubank.repository.TransactionRepository;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

/**
 *
 * @author TanHegemony
 */
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankAccountService bankAccountService;

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

    @Transactional
    public void internalTransfer(TransactionEntity transaction, String toSendMail) {
        try {
            double transactionAmount = Double.parseDouble((String) session.getAttribute("balanceTransfer"));
            transaction.setTransactionAmount(transactionAmount);
            transaction.setTransactionDate(new Date());
            transaction.setTransactionType(TransactionType.INTERNAL_TRANSFER_PAYMENT_ACCOUNT);
            transaction.setBankAccount1((BankAccountEntity) session.getAttribute("bankAccount"));
            transaction.setBankAccount2((BankAccountEntity) session.getAttribute("bankReceiveAccount"));
            transactionRepository.save(transaction);

            BankAccountEntity bankAccountOriginal = (BankAccountEntity) session.getAttribute("bankAccount");
            BankAccountEntity bankAccountReceive = (BankAccountEntity) session.getAttribute("bankReceiveAccount");
            double balanceTransfer = Double.parseDouble((String) session.getAttribute("balanceTransfer"));
            double updateBalanceBAOriginal = bankAccountOriginal.getBalance() - balanceTransfer;
            double updateBalanceBAReceive = bankAccountReceive.getBalance() + balanceTransfer;
            bankAccountOriginal.setBalance(updateBalanceBAOriginal);
            bankAccountReceive.setBalance(updateBalanceBAReceive);
            bankAccountService.saveOrUpdateBankAccount(bankAccountOriginal);
            bankAccountService.saveOrUpdateBankAccount(bankAccountReceive);

            String subject = "Giao dịch chuyển tiền nội ngân hàng thành công!";
            String content = "<h1>Để biết thêm chi tiết, bạn vui lòng click vào đường link bên dưới: </h1>"
                    + "<h3><a href='http://localhost:8080/Spring_Project_Internship_TanTuBank/'>TanTuBank</a></h3>"
                    + "<h3>Cảm ơn bạn! Love 3000 nè!</h3>";
            sendMail("natsutan94@gmail.com", toSendMail, subject, content);
        } catch (MessagingException ex) {
            Logger.getLogger(TransactionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkInternalTransfer(Model model, String balanceTransferString, BankAccountEntity bankAccount, String captcha, String toSendMail) throws MessagingException {
        if (balanceTransferString.equals("")) {
            model.addAttribute("messageBalanceTransfer", "Số tiền chuyển không được để trống");
        } else {
            if (StringUtils.isNumeric(balanceTransferString) == false) {
                model.addAttribute("messageBalanceTransfer", "Số tiền chuyển phải là số");
            } else {
                double balanceTransfer = Double.parseDouble(balanceTransferString);
                if (balanceTransfer > bankAccount.getBalance()) {
                    model.addAttribute("messageBalanceTransfer", "Số dư không đủ");
                } else {
                    if (captcha.equals("")) {
                        model.addAttribute("messageCaptcha", "Captcha không được để trống");
                    } else {
                        if (captcha.length() != 6) {
                            model.addAttribute("messageCaptcha", "Captcha phải có 6 ký tự");
                        } else {
                            if (!captcha.equals(session.getAttribute("captcha"))) {
                                model.addAttribute("messageCaptcha", "Captcha không chính xác");
                                String reloadCaptcha = RandomStringUtils.randomAlphanumeric(6);
                                session.setAttribute("captcha", reloadCaptcha);
                            } else {
                                // send confirm with randomNumber
                                String randomNumber = RandomStringUtils.randomNumeric(6);
                                String subject = "Xác nhận thông tin chuyển khoản nội ngân hàng tại TanTuBank";
                                String content = content = "<h1>Bạn vui lòng nhập mã số xác nhận gồm 6 chữ số vào khung xác nhận giao dịch!</h1>"
                                        + "<h3>Mã xác nhận: " + "<b>" + randomNumber + "</b>" + " </h3>"
                                        + "<h3>Cảm ơn bạn! Love 3000 nè!</h3>";
                                session.setAttribute("randomNumber", randomNumber);
                                sendMail("natsutan94@gmail.com", toSendMail, subject, content);
                                return true;
                            }
                        }
                    }
                }
            }

        }

        return false;
    }

    public boolean checkConfirmCode(Model model, String confirmCode) {
        if (confirmCode.equals("")) {
            model.addAttribute("messageConfirmCode", "Mã xác nhận không được để trống");
        } else {
            if (StringUtils.isNumeric(confirmCode) == false || confirmCode.length() != 6) {
                model.addAttribute("messageConfirmCode", "Mã xác nhận phải là số có 6 chữ số");
            } else {
                if (!confirmCode.equals(session.getAttribute("randomNumber"))) {
                    model.addAttribute("messageConfirmCode", "Mã xác nhận không chính xác");
                } else {
                    return true;
                }
            }
        }
        return false;
    }

}
