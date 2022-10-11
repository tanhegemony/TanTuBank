/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.service;

import com.ivt.spring_project_internship_tantubank.entities.BankAccountEntity;
import com.ivt.spring_project_internship_tantubank.entities.ExternalTransferEntity;
import com.ivt.spring_project_internship_tantubank.entities.StaffEntity;
import com.ivt.spring_project_internship_tantubank.entities.TransactionEntity;
import com.ivt.spring_project_internship_tantubank.enums.TransactionType;
import com.ivt.spring_project_internship_tantubank.repository.TransactionRepository;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
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
    private ExternalTransferService externalTransferService;

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
    public void makeTransfer(String action, TransactionEntity transaction) {
        try {
            double transactionAmount = Double.parseDouble((String) session.getAttribute("balanceTransaction"));
            transaction.setTransactionAmount(transactionAmount);
            transaction.setTransactionDate(new Timestamp(new Date().getTime()));
            if (action.equals("internal_transfer") || action.equals("external_transfer")) {
                transaction.setBankAccount1((BankAccountEntity) session.getAttribute("bankAccount"));
                transaction.setFeeTransaction(0);
                transaction.setStaff(null);
            } else if (action.equals("deposit_for_customer")) {
                transaction.setBankAccount1(null);
                transaction.setFeeTransaction(2000);
                StaffEntity staff = (StaffEntity) session.getAttribute("staff");
                transaction.setStaff(staff);
            }
            transaction.setBankAccount2((BankAccountEntity) session.getAttribute("receiveBankAccount"));
            transaction.setTransactionContent((String) session.getAttribute("contentTransaction"));
            transactionRepository.save(transaction);

            if (action.equals("external_transfer")) {
                ExternalTransferEntity externalTransfer = new ExternalTransferEntity();
                externalTransferService.addExternalTransfer(externalTransfer, transaction);
            }

            double balanceTransaction = Double.parseDouble((String) session.getAttribute("balanceTransaction"));
            if (action.equals("internal_transfer") || action.equals("external_transfer")) {
                BankAccountEntity bankAccountOriginal = (BankAccountEntity) session.getAttribute("bankAccount");
                double updateBalanceBAOriginal = bankAccountOriginal.getBalance() - balanceTransaction;
                bankAccountOriginal.setBalance(updateBalanceBAOriginal);
                bankAccountService.saveOrUpdateBankAccount(bankAccountOriginal);
            }

            BankAccountEntity receiveBankAccount = (BankAccountEntity) session.getAttribute("receiveBankAccount");
            double updateBalanceBAReceive = receiveBankAccount.getBalance() + balanceTransaction;
            receiveBankAccount.setBalance(updateBalanceBAReceive);
            bankAccountService.saveOrUpdateBankAccount(receiveBankAccount);

            if (action.equals("internal_transfer") || action.equals("external_transfer")) {
                String subjectEmailOriginal = "";
                if (action.equals("internal_transfer")) {
                    subjectEmailOriginal = "Giao dịch chuyển tiền nội ngân hàng thành công!";
                }

                if (action.equals("external_transfer")) {
                    subjectEmailOriginal = "Giao dịch chuyển tiền liên ngân hàng thành công!";
                }

                String contentEmailOriginal = "<h1>Bạn vừa chuyển tiền đến số tài khoản " + transaction.getBankAccount2().getAccountNumber() + " </h1>"
                        + "<h1>Để biết thêm chi tiết, bạn vui lòng click vào đường link bên dưới: </h1>"
                        + "<h3><a href='http://localhost:8080/Spring_Project_Internship_TanTuBank/'>TanTuBank</a></h3>"
                        + "<h3>Cảm ơn bạn! Love 3000 nè!</h3>";
                sendMail("natsutan94@gmail.com", transaction.getBankAccount1().getCustomer().getCustomerEmail(), subjectEmailOriginal, contentEmailOriginal);

                String subjectEmailReceive = "Nhận tiền thành công!";
                String contentEmailReceive = "<h1>Bạn vừa được số tài khoản" + transaction.getBankAccount1().getAccountNumber() + " chuyển tiền với nội dung chuyển khoản : " + transaction.getTransactionContent() + "<h1>"
                        + "<h1>Để biết thêm chi tiết, bạn vui lòng click vào đường link bên dưới: </h1>"
                        + "<h3><a href='http://localhost:8080/Spring_Project_Internship_TanTuBank/'>TanTuBank</a></h3>"
                        + "<h3>Cảm ơn bạn! Love 3000 nè!</h3>";
                sendMail("natsutan94@gmail.com", transaction.getBankAccount2().getCustomer().getCustomerEmail(), subjectEmailReceive, contentEmailReceive);
            }else if(action.equals("deposit_for_customer")){
                String subjectEmailReceive = "Nạp tiền thành công!";
                String contentEmailReceive = "<h1>Bạn vừa được ngân hàng nạp tiền vào tài khoản "+receiveBankAccount.getAccountNumber()+" với nội dung chuyển khoản : " + transaction.getTransactionContent() + ". Phí giao dịch: 2,000đ<h1>"
                        + "<h1>Để biết thêm chi tiết, bạn vui lòng click vào đường link bên dưới: </h1>"
                        + "<h3><a href='http://localhost:8080/Spring_Project_Internship_TanTuBank/'>TanTuBank</a></h3>"
                        + "<h3>Cảm ơn bạn! Love 3000 nè!</h3>";
                sendMail("natsutan94@gmail.com", transaction.getBankAccount2().getCustomer().getCustomerEmail(), subjectEmailReceive, contentEmailReceive);
            }

        } catch (MessagingException ex) {
            Logger.getLogger(TransactionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkTransaction(Model model, String action, String contentTransaction,
            String balanceTransactionString, BankAccountEntity bankAccount,
            String captcha, String toSendMail) throws MessagingException {
        if (balanceTransactionString.equals("")) {
            model.addAttribute("messageBalanceTransaction", "Số tiền chuyển không được để trống");
        } else {
            if (StringUtils.isNumeric(balanceTransactionString) == false) {
                model.addAttribute("messageBalanceTransaction", "Số tiền chuyển phải là số");
            } else {
                double balanceTransfer = Double.parseDouble(balanceTransactionString);
                if (balanceTransfer > bankAccount.getBalance() && !action.equals("deposit_for_customer")) {
                    model.addAttribute("messageBalanceTransaction", "Số dư không đủ");
                } else {
                    if (contentTransaction.equals("")) {
                        model.addAttribute("messageContentTransfer", "Nội dung chuyển khoản không được để trống");
                    } else if (contentTransaction.length() > 50) {
                        model.addAttribute("messageContentTransfer", "Nội dung chuyển khoản không được quá 50 ký tự");
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
                                    if (action.equals("internal_transfer") || action.equals("external_transfer")) {
                                        String randomNumber = RandomStringUtils.randomNumeric(6);
                                        String subject = "";
                                        if (action.equals("internal_transfer")) {
                                            subject = "Xác nhận thông tin chuyển khoản nội ngân hàng tại TanTuBank";
                                        } else if (action.equals("external_transfer")) {
                                            subject = "Xác nhận thông tin chuyển khoản liên ngân hàng tại TanTuBank";
                                        }
                                        String content = content = "<h1>Bạn vui lòng nhập mã số xác nhận gồm 6 chữ số vào khung xác nhận giao dịch!</h1>"
                                                + "<h3>Mã xác nhận: " + "<b>" + randomNumber + "</b>" + " </h3>"
                                                + "<h3>Cảm ơn bạn! Love 3000 nè!</h3>";
                                        session.setAttribute("randomNumber", randomNumber);
                                        sendMail("natsutan94@gmail.com", toSendMail, subject, content);
                                    }
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
