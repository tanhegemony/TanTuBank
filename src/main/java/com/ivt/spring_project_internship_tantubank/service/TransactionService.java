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
            String balanceTransactionString = (String) session.getAttribute("balanceTransaction");
            String balanceTransaction =  balanceTransactionString.replaceAll(",", "");
            double transactionAmount = Double.parseDouble(balanceTransaction);
            transaction.setTransactionAmount(transactionAmount);
            transaction.setTransactionDate(new Timestamp(new Date().getTime()));
            if (action.equals("internal_transfer") || action.equals("external_transfer")) {
                transaction.setBankAccount1((BankAccountEntity) session.getAttribute("bankAccount"));
                if(action.equals("internal_transfer")){
                    transaction.setFeeTransaction(0);
                }else if(action.equals("external_transfer")){
                     transaction.setFeeTransaction(2000);
                }
                transaction.setTantuBankAddress(null);
                transaction.setStaff(null);
            } else if (action.equals("deposit_for_customer")) {
                transaction.setBankAccount1(null);
                transaction.setFeeTransaction(2000);
                StaffEntity staff = (StaffEntity) session.getAttribute("staff");
                transaction.setStaff(staff);
                String tantuBankAddress = (String) session.getAttribute("tantuBankAddress");
                transaction.setTantuBankAddress(tantuBankAddress);
            }
            transaction.setBankAccount2((BankAccountEntity) session.getAttribute("receiveBankAccount"));
            transaction.setTransactionContent((String) session.getAttribute("contentTransaction"));
            transactionRepository.save(transaction);

            if (action.equals("external_transfer")) {
                ExternalTransferEntity externalTransfer = new ExternalTransferEntity();
                externalTransferService.addExternalTransfer(externalTransfer, transaction);
            }

            if (action.equals("internal_transfer") || action.equals("external_transfer")) {
                BankAccountEntity bankAccountOriginal = (BankAccountEntity) session.getAttribute("bankAccount");
                double updateBalanceBAOriginal = bankAccountOriginal.getBalance() - transactionAmount;
                bankAccountOriginal.setBalance(updateBalanceBAOriginal);
                bankAccountService.saveOrUpdateBankAccount(bankAccountOriginal);
            }

            BankAccountEntity receiveBankAccount = (BankAccountEntity) session.getAttribute("receiveBankAccount");
            double updateBalanceBAReceive = receiveBankAccount.getBalance() + transactionAmount;
            receiveBankAccount.setBalance(updateBalanceBAReceive);
            bankAccountService.saveOrUpdateBankAccount(receiveBankAccount);

            if (action.equals("internal_transfer") || action.equals("external_transfer")) {
                String subjectEmailOriginal = "";
                if (action.equals("internal_transfer")) {
                    subjectEmailOriginal = "Giao d???ch chuy???n ti???n n???i ng??n h??ng th??nh c??ng!";
                }

                if (action.equals("external_transfer")) {
                    subjectEmailOriginal = "Giao d???ch chuy???n ti???n li??n ng??n h??ng th??nh c??ng!";
                }

                String contentEmailOriginal = "<h2>B???n v???a chuy???n ti???n ?????n s??? t??i kho???n " + transaction.getBankAccount2().getAccountNumber() + " </h2>"
                        + "<h4>????? bi???t th??m chi ti???t, b???n vui l??ng click v??o ???????ng link b??n d?????i: </h4>"
                        + "<h5><a href='http://localhost:8080/Spring_Project_Internship_TanTuBank/'>TanTuBank</a></h5>"
                        + "<h5>C???m ??n b???n! Love 3000 n??!</h5>";
                sendMail("natsutan94@gmail.com", transaction.getBankAccount1().getCustomer().getCustomerEmail(), subjectEmailOriginal, contentEmailOriginal);

                String subjectEmailReceive = "Nh???n ti???n th??nh c??ng!";
                String contentEmailReceive = "<h2>B???n v???a ???????c s??? t??i kho???n" + transaction.getBankAccount1().getAccountNumber() + " chuy???n ti???n v???i n???i dung chuy???n kho???n : " + transaction.getTransactionContent() + "<h2>"
                        + "<h4>????? bi???t th??m chi ti???t, b???n vui l??ng click v??o ???????ng link b??n d?????i: </h4>"
                        + "<h5><a href='http://localhost:8080/Spring_Project_Internship_TanTuBank/'>TanTuBank</a></h5>"
                        + "<h5>C???m ??n b???n! Love 3000 n??!</h5>";
                sendMail("natsutan94@gmail.com", transaction.getBankAccount2().getCustomer().getCustomerEmail(), subjectEmailReceive, contentEmailReceive);
            }else if(action.equals("deposit_for_customer")){
                String subjectEmailReceive = "N???p ti???n th??nh c??ng!";
                String contentEmailReceive = "<h2>B???n v???a ???????c ng??n h??ng n???p ti???n v??o t??i kho???n "+receiveBankAccount.getAccountNumber()+" v???i n???i dung chuy???n kho???n : " + transaction.getTransactionContent() 
                        + ". Ph?? giao d???ch: 2,000??. T???i ng??n h??ng TanTuBank - ?????a ch???: "+ transaction.getTantuBankAddress() +"<h2>"
                        + "<h4>????? bi???t th??m chi ti???t, b???n vui l??ng click v??o ???????ng link b??n d?????i: </h4>"
                        + "<h5><a href='http://localhost:8080/Spring_Project_Internship_TanTuBank/'>TanTuBank</a></h5>"
                        + "<h5>C???m ??n b???n! Love 3000 n??!</h5>";
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
            model.addAttribute("messageBalanceTransaction", "S??? ti???n chuy???n kh??ng ???????c ????? tr???ng");
        } else {
            balanceTransactionString = balanceTransactionString.replaceAll(",", "");
            if (StringUtils.isNumeric(balanceTransactionString) == false) {
                model.addAttribute("messageBalanceTransaction", "S??? ti???n chuy???n ph???i l?? s???");
            } else {
                double balanceTransfer = Double.parseDouble(balanceTransactionString);
                if(action.equals("external_transfer")){
                    balanceTransfer = balanceTransfer + 2000;
                }
                if (balanceTransfer > bankAccount.getBalance() 
                        && !action.equals("deposit_for_customer")) {
                    model.addAttribute("messageBalanceTransaction", "S??? d?? kh??ng ?????");
                } else {
                    if (contentTransaction.equals("")) {
                        model.addAttribute("messageContentTransfer", "N???i dung chuy???n kho???n kh??ng ???????c ????? tr???ng");
                    } else if (contentTransaction.length() > 50) {
                        model.addAttribute("messageContentTransfer", "N???i dung chuy???n kho???n kh??ng ???????c qu?? 50 k?? t???");
                    } else {
                        if (captcha.equals("") && !action.equals("deposit_for_customer")) {
                            model.addAttribute("messageCaptcha", "Captcha kh??ng ???????c ????? tr???ng");
                        } else {
                            if (captcha.length() != 6 && !action.equals("deposit_for_customer")) {
                                model.addAttribute("messageCaptcha", "Captcha ph???i c?? 6 k?? t???");
                            } else {
                                if (!captcha.equals(session.getAttribute("captcha")) && !action.equals("deposit_for_customer")) {
                                    model.addAttribute("messageCaptcha", "Captcha kh??ng ch??nh x??c");
                                    String reloadCaptcha = RandomStringUtils.randomAlphanumeric(6);
                                    session.setAttribute("captcha", reloadCaptcha);
                                } else {
                                    // send confirm with randomNumber
                                    if (action.equals("internal_transfer") || action.equals("external_transfer")) {
                                        String randomNumber = RandomStringUtils.randomNumeric(6);
                                        String subject = "";
                                        if (action.equals("internal_transfer")) {
                                            subject = "X??c nh???n th??ng tin chuy???n kho???n n???i ng??n h??ng t???i TanTuBank";
                                        } else if (action.equals("external_transfer")) {
                                            subject = "X??c nh???n th??ng tin chuy???n kho???n li??n ng??n h??ng t???i TanTuBank";
                                        }
                                        String content = content = "<h1>B???n vui l??ng nh???p m?? s??? x??c nh???n g???m 6 ch??? s??? v??o khung x??c nh???n giao d???ch!</h1>"
                                                + "<h3>M?? x??c nh???n: " + "<b>" + randomNumber + "</b>" + " </h3>"
                                                + "<h3>C???m ??n b???n! Love 3000 n??!</h3>";
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
            model.addAttribute("messageConfirmCode", "M?? x??c nh???n kh??ng ???????c ????? tr???ng");
        } else {
            if (StringUtils.isNumeric(confirmCode) == false || confirmCode.length() != 6) {
                model.addAttribute("messageConfirmCode", "M?? x??c nh???n ph???i l?? s??? c?? 6 ch??? s???");
            } else {
                if (!confirmCode.equals(session.getAttribute("randomNumber"))) {
                    model.addAttribute("messageConfirmCode", "M?? x??c nh???n kh??ng ch??nh x??c");
                } else {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void saveOrUpdateTransaction(TransactionEntity transaction){
        transactionRepository.save(transaction);
    }

}
