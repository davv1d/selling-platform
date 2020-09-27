package com.pik.controller;

import com.pik.component.ResetPasswordCommandGenerator;
import com.pik.dto.ResetPasswordDto;
import com.pik.dto.ResettingUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPasswordResetController {
    private final ResetPasswordCommandGenerator resetPasswordCommandGenerator;

    public UserPasswordResetController(ResetPasswordCommandGenerator resetPasswordCommandGenerator) {
        this.resetPasswordCommandGenerator = resetPasswordCommandGenerator;
    }

    @PostMapping("/code")
    public void generateResetCode(@RequestBody ResettingUser resettingUser) {
        resetPasswordCommandGenerator.generateResetCode(resettingUser);
    }

    @PostMapping("/reset")
    public void resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        resetPasswordCommandGenerator.resetPassword(resetPasswordDto);
    }

}
