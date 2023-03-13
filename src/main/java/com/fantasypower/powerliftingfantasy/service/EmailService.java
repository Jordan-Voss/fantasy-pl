package com.fantasypower.powerliftingfantasy.service;

import com.fantasypower.powerliftingfantasy.util.EmailSender;
import jakarta.mail.Address;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Override
    @Async
    public void send(String to, String email) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setFrom("NO-REPLY@fantasypowerlifting.com");
        message.setRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject("Confirm Your Email");
        message.setContent(email, "text/html; charset=utf-8");
        javaMailSender.send(message);
        LOGGER.info("Email Sent");
    }
}
