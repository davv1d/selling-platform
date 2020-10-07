package com.pik.controller;

import com.pik.sender.ResetPasswordCommandSender;
import com.pik.domain.dto.ResetPasswordDto;
import com.pik.domain.dto.ResettingUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserPasswordResetController {
    private final ResetPasswordCommandSender resetPasswordCommandSender;

    @PostMapping("/code")
    public void generateResetCode(@RequestBody ResettingUser resettingUser) {
        resetPasswordCommandSender.generateResetCode(resettingUser);
    }

    @PostMapping("/reset")
    public void resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        resetPasswordCommandSender.resetPassword(resetPasswordDto);
    }
}
