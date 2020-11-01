package com.pik.chat.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

public class SendMessageCommand {
    @TargetAggregateIdentifier
    public final String id;
    public final String senderId;
    public final String recipientId;
    public final String advertisementId;
    public final LocalDateTime when;
    public final String content;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public SendMessageCommand(@JsonProperty("id") String id,
                                @JsonProperty("senderId") String senderId,
                                @JsonProperty("recipientId") String recipientId,
                                @JsonProperty("advertisementId") String advertisementId,
                                @JsonProperty("when") LocalDateTime when,
                                @JsonProperty("content") String content) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.advertisementId = advertisementId;
        this.when = when;
        this.content = content;
    }
}
