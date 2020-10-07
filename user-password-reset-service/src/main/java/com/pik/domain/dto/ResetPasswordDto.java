package com.pik.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordDto {
    private String id;
    private String code;
    private String newPassword;
}
