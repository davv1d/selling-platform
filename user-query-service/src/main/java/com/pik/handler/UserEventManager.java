package com.pik.handler;

import com.pik.aggregate.UserStatus;
import com.pik.entity.User;
import com.pik.event.UserActivatedEvent;
import com.pik.event.UserChangePasswordEvent;
import com.pik.event.UserCreatedEvent;
import com.pik.event.UserDeactivatedEvent;
import com.pik.repository.UserRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserEventManager {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserEventManager.class);

    public UserEventManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventHandler
    public void on(UserCreatedEvent event) {
        this.logger.info("Save user with id " + event.userId + " and email " + event.email);
        User userEntity = new User(event.userId, event.email, event.password, event.status);
        userRepository.save(userEntity);
    }

    @EventHandler
    public void on(UserActivatedEvent event) {
        this.logger.info("Activate user with id " + event.userId);
        save(event.userId, u -> new User(u.getId(), u.getEmail(), u.getPassword(), UserStatus.ACTIVATE.toString()));
    }

    @EventHandler
    public void on(UserDeactivatedEvent event) {
        this.logger.info("Deactivate user with id " + event.userId);
        save(event.userId, u -> new User(u.getId(), u.getEmail(), u.getPassword(), UserStatus.DEACTIVATE.toString()));
    }

    @EventHandler
    public void on(UserChangePasswordEvent event) {
        this.logger.info("Change password. User with id " + event.userId);
        save(event.userId, u -> new User(u.getId(), u.getEmail(), event.password, u.getStatus()));
    }

    private void save(String userId, Function<User, User> mapUser) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with this id (" + userId + ") does not exist"));
        User newUser = mapUser.apply(user);
        userRepository.save(newUser);
    }
}
