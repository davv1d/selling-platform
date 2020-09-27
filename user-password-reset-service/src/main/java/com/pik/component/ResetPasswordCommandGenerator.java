package com.pik.component;

import com.pik.command.password.GenerateResetPasswordCodeCommand;
import com.pik.command.password.ResetPasswordCommand;
import com.pik.dto.ResetPasswordDto;
import com.pik.dto.ResettingUser;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class ResetPasswordCommandGenerator {
    private final CommandGateway commandGateway;
    private final CodeGenerator codeGenerator;

    public ResetPasswordCommandGenerator(CommandGateway commandGateway, CodeGenerator codeGenerator) {
        this.commandGateway = commandGateway;
        this.codeGenerator = codeGenerator;
    }

    public void generateResetCode(ResettingUser resettingUser) {
        String id = UUID.randomUUID().toString();
        Instant expirationDate = Instant.now().plusSeconds(300);
        String token = codeGenerator.generateCommonLangCode();
        GenerateResetPasswordCodeCommand command = new GenerateResetPasswordCodeCommand(
                id,
                resettingUser.getUserId(),
                resettingUser.getEmail(),
                token,
                expirationDate);
        this.commandGateway.send(command);
    }

    public void resetPassword(ResetPasswordDto resetPasswordDto) {
        this.commandGateway.send(new ResetPasswordCommand(resetPasswordDto.getId(), resetPasswordDto.getCode(), resetPasswordDto.getNewPassword(), Instant.now()));
    }
}
