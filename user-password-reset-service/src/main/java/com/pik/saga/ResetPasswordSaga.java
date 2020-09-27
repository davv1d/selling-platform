package com.pik.saga;

import com.pik.command.ChangeUserPasswordCommand;
import com.pik.event.PasswordResetEvent;
import com.pik.event.UserChangePasswordEvent;
import com.pik.repository.PasswordTokenCrudRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Saga
public class ResetPasswordSaga {
    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient PasswordTokenCrudRepository passwordTokenCrudRepository;

    @Autowired
    private transient PasswordEncoder passwordEncoder;

    @StartSaga
    @SagaEventHandler(associationProperty = "userId")
    public void on(PasswordResetEvent event) {
        System.out.println("Saga reset password step 1");
        String encodePassword = this.passwordEncoder.encode(event.newPassword);
        this.commandGateway.send(new ChangeUserPasswordCommand(event.userId, encodePassword));
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "userId")
    public void on(UserChangePasswordEvent event) {
        System.out.println("Saga reset password step 2 - end of saga");
        passwordTokenCrudRepository.findByUserId(event.userId)
                .ifPresent(passwordToken -> passwordTokenCrudRepository.deleteById(passwordToken.getId()));
    }
}
