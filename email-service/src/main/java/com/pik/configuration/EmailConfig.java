package com.pik.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailConfig {
    @Value("${activate.url}")
    private String activateUrl;

    @Value("${admin.mail}")
    private String adminMail;

    public String getActivateUrl() {
        return activateUrl;
    }

    public String getAdminMail() {
        return adminMail;
    }
}
