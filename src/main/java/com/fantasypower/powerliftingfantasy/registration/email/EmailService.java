package com.fantasypower.powerliftingfantasy.registration.email;

import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
        Address recipient;
        message.setRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject("Confirm Your Email");
        message.setContent(email, "text/html; charset=utf-8");
        javaMailSender.send(message);
        LOGGER.info("Email Sent");
        // MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
        // mimeMessageHelper.setText(email, true);
        // mimeMessageHelper.setTo(to);
        // mimeMessageHelper.setSubject("Confirm Your Email");
        // mimeMessageHelper.setFrom("NO_REPLY@fantasy-powerlifting.com");
    }
}
