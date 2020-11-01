package com.pik.domain;


import com.pik.chat.command.CreateMessageCommand;
import com.pik.chat.event.MessageCreatedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Aggregate
@Entity
@NoArgsConstructor
@Getter
public class Message {
    @Id
    @AggregateIdentifier
    private String id;
    private String senderId;
    private String recipientId;
    private String advertisementId;
    private LocalDateTime when;
    private String content;

    @CommandHandler
    public Message(CreateMessageCommand c) {
        this.id = c.id;
        this.senderId = c.senderId;
        this.recipientId = c.recipientId;
        this.advertisementId = c.advertisementId;
        this.when = c.when;
        this.content = c.content;
        AggregateLifecycle.apply(new MessageCreatedEvent(c.id, c.senderId, c.recipientId,
                c.advertisementId, c.when, c.content));
    }
}
