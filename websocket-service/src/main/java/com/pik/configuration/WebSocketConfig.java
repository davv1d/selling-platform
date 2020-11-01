package com.pik.configuration;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(@NotNull MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app");
        config.enableStompBrokerRelay( "/topic/")
                .setRelayHost("192.168.99.100")
                .setRelayPort(61613)
                .setClientLogin("guest")
                .setClientPasscode("guest");

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*");
//        .withSockJS();
    }
}
