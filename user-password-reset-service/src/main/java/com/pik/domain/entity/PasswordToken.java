package com.pik.domain.entity;

import com.pik.password.command.GenerateResetPasswordCodeCommand;
import com.pik.password.command.ResetPasswordCommand;
import com.pik.password.event.PasswordResetEvent;
import com.pik.password.event.ResetPasswordCodeGeneratedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.deadline.DeadlineManager;
import org.axonframework.deadline.annotation.DeadlineHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Duration;

@Aggregate
@Entity
@NoArgsConstructor
@Getter
public class PasswordToken {
    @Id
    @AggregateIdentifier
    private String id;
    private String userId;
    private String token;
    private boolean active;
    private String deadlineId;

    @CommandHandler
    public PasswordToken(GenerateResetPasswordCodeCommand command, DeadlineManager deadlineManager) {
        this.id = command.id;
        this.userId = command.userId;
        this.token = command.token;
        this.active = true;
        this.deadlineId = deadlineManager.schedule(Duration.ofSeconds(command.secondsOfActivity), "token");
        System.out.println("construct password token id " + command.id + " | " + command.token);
        AggregateLifecycle.apply(new ResetPasswordCodeGeneratedEvent(command.id, command.userId, command.email, command.token));
    }

    @CommandHandler
    public void on(ResetPasswordCommand command, DeadlineManager deadlineManager) {
        validateToken(command, () -> {
            System.out.println("good code go to saga");
            deadlineManager.cancelSchedule("token", deadlineId);
            active = false;
            AggregateLifecycle.apply(new PasswordResetEvent(this.id, this.userId, command.newPassword));
        });
    }

    @DeadlineHandler
    public void deactivateToken() {
        active = false;
    }

    private void validateToken(ResetPasswordCommand command, Runnable sendEvent) {
        if (!active) {
            throw new IllegalStateException("The code has expired");
        }
        if (!token.equals(command.code)) {
            throw new IllegalStateException("Incorrect code");
        }
        sendEvent.run();
    }
}
