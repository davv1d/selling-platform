package com.pik.aggregate;

import com.pik.command.password.GenerateResetPasswordCodeCommand;
import com.pik.command.password.ResetPasswordCommand;
import com.pik.event.PasswordResetEvent;
import com.pik.event.ResetPasswordCodeGeneratedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Aggregate
@Entity
public class PasswordToken {
    @Id
    @AggregateIdentifier
    private String id;
    private String userId;
    private String email;
    private String token;
    private Instant expirationDate;

    public PasswordToken() {
    }

    @CommandHandler
    public PasswordToken(GenerateResetPasswordCodeCommand command) {
        this.id = command.id;
        this.userId = command.userId;
        this.email = command.email;
        this.token = command.token;
        this.expirationDate = command.expirationDate;
        System.out.println("construct password token id " + command.id + " | " + command.token);
        AggregateLifecycle.apply(new ResetPasswordCodeGeneratedEvent(command.id, command.userId, command.email, command.token, command.expirationDate));
    }

    @CommandHandler
    public void on(ResetPasswordCommand command) {
        if (!this.token.equals(command.code)) {
            throw new IllegalStateException("Incorrect code");
        } else if (command.when.isAfter(this.expirationDate)) {
            throw new IllegalStateException("The code has expired");
        } else {
            System.out.println("good code go to saga");
            this.expirationDate = Instant.now();
            AggregateLifecycle.apply(new PasswordResetEvent(this.id, this.userId, command.newPassword));
        }
    }

    public String getId() {
        return id;
    }
}
