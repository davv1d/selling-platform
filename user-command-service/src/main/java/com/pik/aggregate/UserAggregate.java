package com.pik.aggregate;

import com.pik.command.ActivateUserCommand;
import com.pik.command.ChangeUserPasswordCommand;
import com.pik.command.CreateUserCommand;
import com.pik.command.DeactivateUserCommand;
import com.pik.event.UserActivatedEvent;
import com.pik.event.UserChangePasswordEvent;
import com.pik.event.UserCreatedEvent;
import com.pik.event.UserDeactivatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.pik.aggregate.UserStatus.*;

@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String userId;
    private String email;
    private String password;
    private UserStatus status;
    private final transient Logger logger = LoggerFactory.getLogger(UserAggregate.class);

    public UserAggregate() {
    }

    @CommandHandler
    public UserAggregate(CreateUserCommand command) {
        logger.info("Create user with id: " + command.userId + " and email: " + command.email);
        AggregateLifecycle.apply(new UserCreatedEvent(command.userId, command.email, command.password, command.status));
    }

    @CommandHandler
    public void handle(ActivateUserCommand command) {
        if (this.status.equals(ACTIVATE)) {
            throw new IllegalStateException("User is activated");
        }
        logger.info("Activate user with id: " + command.userId);
        AggregateLifecycle.apply(new UserActivatedEvent(command.userId));
    }

    @CommandHandler
    public void handle(DeactivateUserCommand command) {
        if (this.status.equals(DEACTIVATE) || this.status.equals(INITIALIZE)) {
            throw new IllegalStateException("User is not activated. Cannot deactivate");
        }
        logger.info("Deactivate user with id: " + command.userId);
        AggregateLifecycle.apply(new UserDeactivatedEvent(command.userId));
    }

    @CommandHandler
    public void handle(ChangeUserPasswordCommand command) {
        if (this.status.equals(INITIALIZE) || this.status.equals(DEACTIVATE)) {
            throw new IllegalStateException("User is not activated.");
        }
        logger.info("Change user password. User id is: " + command.userId);
        AggregateLifecycle.apply(new UserChangePasswordEvent(command.userId, command.password));
    }

    @EventSourcingHandler
    protected void on(UserCreatedEvent event) {
        this.userId = event.userId;
        this.email = event.email;
        this.password = event.password;
        this.status = valueOf(event.status);
    }

    @EventSourcingHandler
    protected void on(UserActivatedEvent event) {
        this.status = ACTIVATE;
    }

    @EventSourcingHandler
    protected void on(UserDeactivatedEvent event) {
        this.status = DEACTIVATE;
    }

    @EventSourcingHandler
    protected void on(UserChangePasswordEvent event) {
        this.password = event.password;
    }

}
