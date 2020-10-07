package com.pik.sender;

import com.pik.domain.dto.ResetPasswordDto;
import com.pik.domain.dto.ResettingUser;
import com.pik.password.command.GenerateResetPasswordCodeCommand;
import com.pik.password.command.ResetPasswordCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ResetPasswordCommandSender {
    private final CommandGateway commandGateway;
    private final CodeGenerator codeGenerator;

    public void generateResetCode(ResettingUser resettingUser) {
        String id = UUID.randomUUID().toString();
        String token = codeGenerator.generateCommonLangCode();
        GenerateResetPasswordCodeCommand command = new GenerateResetPasswordCodeCommand(
                id,
                resettingUser.getUserId(),
                resettingUser.getEmail(),
                token,
                300);
        this.commandGateway.send(command);
    }

    public void resetPassword(ResetPasswordDto resetPasswordDto) {
        this.commandGateway.send(new ResetPasswordCommand(resetPasswordDto.getId(), resetPasswordDto.getCode(), resetPasswordDto.getNewPassword(), Instant.now()));
    }
}
