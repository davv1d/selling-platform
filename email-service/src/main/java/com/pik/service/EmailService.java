package com.pik.service;

import com.pik.dto.Mail;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSimpleMessage(final Mail mail) {
        try {
            SimpleMailMessage mailMessage = createMailMessage(mail);
            javaMailSender.send(mailMessage);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    private SimpleMailMessage createMailMessage(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getMailTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getMessage());
        return message;
    }
}
