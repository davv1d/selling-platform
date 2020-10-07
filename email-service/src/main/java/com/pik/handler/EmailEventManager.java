package com.pik.handler;

import com.pik.configuration.EmailConfig;
import com.pik.dto.Mail;
import com.pik.password.event.ResetPasswordCodeGeneratedEvent;
import com.pik.service.EmailService;
import com.pik.user.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailEventManager {
    private final EmailConfig emailConfig;
    private final EmailService emailService;
    private final Logger logger = LoggerFactory.getLogger(EmailEventManager.class);

    @EventHandler
    private void on(UserCreatedEvent event) {
        String message = emailConfig.getActivateUrl() + event.userId;
        Mail mail = new Mail(emailConfig.getAdminMail(), "Created user event", message);
        // TODO: 23.09.2020 Comment method because I do not want to send email
//        emailService.sendSimpleMessage(mail);
        this.logger.info("Email (create user) was sent to " + event.email + " User id is " + event.userId);
    }

    @EventHandler
    private void on(ResetPasswordCodeGeneratedEvent event) {
        String message = event.token + " time ";
        Mail mail = new Mail(emailConfig.getAdminMail(), "Reset user password", message);
        // TODO: 23.09.2020 Comment method because I do not want to send email
//        emailService.sendSimpleMessage(mail);
        this.logger.info("Reset password id " + event.id);
        this.logger.info("Email (reset password) was sent to " + event.email + " User id is " + event.userId);
    }
}

