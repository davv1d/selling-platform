package com.pik.dto;

import lombok.Value;

@Value
public class Mail {
    String mailTo;
    String subject;
    String message;
}

