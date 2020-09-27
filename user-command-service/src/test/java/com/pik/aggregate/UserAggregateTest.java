package com.pik.aggregate;

import com.pik.command.ActivateUserCommand;
import com.pik.command.ChangeUserPasswordCommand;
import com.pik.command.CreateUserCommand;
import com.pik.command.DeactivateUserCommand;
import com.pik.event.UserActivatedEvent;
import com.pik.event.UserChangePasswordEvent;
import com.pik.event.UserCreatedEvent;
import com.pik.event.UserDeactivatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class UserAggregateTest {
    private FixtureConfiguration<UserAggregate> fixture;

    @Before
    public void setUp() {
        this.fixture = new AggregateTestFixture<>(UserAggregate.class);
    }


    @Test
    public void shouldReturnSuccessCreateUserCommand() {
        String userId = UUID.randomUUID().toString();
        fixture.givenNoPriorActivity()
                .when(new CreateUserCommand(userId, "test@email.com", "password", UserStatus.INITIALIZE.toString()))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new UserCreatedEvent(userId, "test@email.com", "password", UserStatus.INITIALIZE.toString()));
    }

    @Test
    public void shouldReturnSuccessActivateUserCommand() {
        String userId = UUID.randomUUID().toString();
        fixture.given(new UserCreatedEvent(userId, "test@email.com", "password", UserStatus.INITIALIZE.toString()))
                .when(new ActivateUserCommand(userId))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new UserActivatedEvent(userId));

    }

    @Test
    public void shouldThrowError_TryActivateOfActivatedUser() {
        String userId = UUID.randomUUID().toString();
        fixture.given(
                new UserCreatedEvent(userId, "test@email.com", "password", UserStatus.INITIALIZE.toString()),
                new UserActivatedEvent(userId))
                .when(new ActivateUserCommand(userId))
                .expectException(IllegalStateException.class);
    }

    @Test
    public void shouldReturnSuccessDeactivateUserCommand() {
        String userId = UUID.randomUUID().toString();
        fixture.given(
                new UserCreatedEvent(userId, "test@email.com", "password", UserStatus.INITIALIZE.toString()),
                new UserActivatedEvent(userId))
                .when(new DeactivateUserCommand(userId))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new UserDeactivatedEvent(userId));

    }

    @Test
    public void shouldThrowError_DeactivateOfDeactivatedUser() {
        String userId = UUID.randomUUID().toString();
        fixture.given(
                new UserCreatedEvent(userId, "test@email.com", "password", UserStatus.INITIALIZE.toString()),
                new UserDeactivatedEvent(userId))
                .when(new DeactivateUserCommand(userId))
                .expectException(IllegalStateException.class);
    }

    @Test
    public void shouldThrowError_DeactivateOfInitializedUser() {
        String userId = UUID.randomUUID().toString();
        fixture.given(
                new UserCreatedEvent(userId, "test@email.com", "password", UserStatus.INITIALIZE.toString()))
                .when(new DeactivateUserCommand(userId))
                .expectException(IllegalStateException.class);
    }

    @Test
    public void shouldReturnSuccess_ChangeUserPassword() {
        String userId = UUID.randomUUID().toString();
        fixture.given(
                new UserCreatedEvent(userId, "test@email.com", "password", UserStatus.INITIALIZE.toString()),
                new UserActivatedEvent(userId))
                .when(new ChangeUserPasswordCommand(userId, "changed password"))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new UserChangePasswordEvent(userId, "changed password"));
    }

    @Test
    public void shouldThrowError_ChangePasswordOfDeactivatedUser() {
        String userId = UUID.randomUUID().toString();
        fixture.given(
                new UserCreatedEvent(userId, "test@email.com", "password", UserStatus.INITIALIZE.toString()),
                new UserDeactivatedEvent(userId))
                .when(new ChangeUserPasswordCommand(userId, "changed password"))
                .expectException(IllegalStateException.class);
    }

    @Test
    public void shouldThrowError_ChangePasswordOfInitializedUser() {
        String userId = UUID.randomUUID().toString();
        fixture.given(
                new UserCreatedEvent(userId, "test@email.com", "password", UserStatus.INITIALIZE.toString()))
                .when(new ChangeUserPasswordCommand(userId, "changed password"))
                .expectException(IllegalStateException.class);
    }
}
