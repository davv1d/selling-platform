package com.pik.handler;

import com.pik.entity.User;
import com.pik.repository.UserRepository;
import com.pik.user.UserStatus;
import com.pik.user.event.UserActivatedEvent;
import com.pik.user.event.UserChangePasswordEvent;
import com.pik.user.event.UserCreatedEvent;
import com.pik.user.event.UserDeactivatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserEventHandler {
    private final UserRepository userRepository;

    @EventHandler
    public void on(UserCreatedEvent event) {
        log.info("Save user with id " + event.userId + " and email " + event.email);
        User userEntity = new User(event.userId, event.email, event.password, event.status);
        userRepository.save(userEntity);
    }

    @EventHandler
    public void on(UserActivatedEvent event) {
        log.info("Activate user with id " + event.userId);
        save(event.userId, u -> new User(u.getId(), u.getEmail(), u.getPassword(), UserStatus.ACTIVATE.toString()));
    }

    @EventHandler
    public void on(UserDeactivatedEvent event) {
        log.info("Deactivate user with id " + event.userId);
        save(event.userId, u -> new User(u.getId(), u.getEmail(), u.getPassword(), UserStatus.DEACTIVATE.toString()));
    }

    @EventHandler
    public void on(UserChangePasswordEvent event) {
        log.info("Change password. User with id " + event.userId);
        save(event.userId, u -> new User(u.getId(), u.getEmail(), event.password, u.getStatus()));
    }

    private void save(String userId, Function<User, User> mapUser) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with this id (" + userId + ") does not exist"));
        User newUser = mapUser.apply(user);
        userRepository.save(newUser);
    }
}
