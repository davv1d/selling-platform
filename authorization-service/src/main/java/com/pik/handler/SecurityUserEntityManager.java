package com.pik.handler;

import com.pik.domain.SecurityUser;
import com.pik.security.repository.SecurityUserRepository;
import com.pik.user.UserStatus;
import com.pik.user.event.UserActivatedEvent;
import com.pik.user.event.UserChangePasswordEvent;
import com.pik.user.event.UserCreatedEvent;
import com.pik.user.event.UserDeactivatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class SecurityUserEntityManager {
    private final SecurityUserRepository securityUserRepository;

    @EventSourcingHandler
    public void on(UserCreatedEvent event) {
        if (!securityUserRepository.findByEmail(event.email).isPresent()) {
            SecurityUser securityUser = new SecurityUser(event.userId, event.email, event.password, event.role, event.status);
            System.out.println(securityUserRepository.save(securityUser));
        } else {
            throw new IllegalStateException("email exists in database");
        }
    }

    @EventSourcingHandler
    public void on(UserChangePasswordEvent event) {
        SecurityUser securityUser = securityUserRepository.findById(event.userId)
                .orElseThrow(() -> new UsernameNotFoundException("User with id " + event.userId + " does not exist"));
        securityUser.setPassword(event.password);
        System.out.println(securityUserRepository.save(securityUser));
    }

    @EventSourcingHandler
    public void on(UserActivatedEvent event) {
        SecurityUser securityUser = securityUserRepository.findById(event.userId)
                .orElseThrow(() -> new UsernameNotFoundException("User with id " + event.userId + " does not exist"));
        securityUser.setUserStatus(UserStatus.ACTIVATE.toString());
        System.out.println(securityUserRepository.save(securityUser));
    }

    @EventSourcingHandler
    public void on(UserDeactivatedEvent event) {
        SecurityUser securityUser = securityUserRepository.findById(event.userId)
                .orElseThrow(() -> new UsernameNotFoundException("User with id " + event.userId + " does not exist"));
        securityUser.setUserStatus(UserStatus.DEACTIVATE.toString());
        System.out.println(securityUserRepository.save(securityUser));
    }
}
