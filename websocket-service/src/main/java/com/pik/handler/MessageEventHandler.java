package com.pik.handler;

import com.pik.chat.event.MessageCreatedEvent;
import com.pik.dto.Message;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageEventHandler {
    private final SimpMessagingTemplate messagingTemplate;

    @EventHandler
    public void handle(MessageCreatedEvent event) {
        Message message = new Message(event.advertisementId, event.when, event.content);
        messagingTemplate.convertAndSend("/topic/private.chat." + event.recipientId, message);
    }
}


