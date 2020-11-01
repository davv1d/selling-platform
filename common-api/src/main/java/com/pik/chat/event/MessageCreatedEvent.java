package com.pik.chat.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class MessageCreatedEvent {
    public final String id;
    public final String senderId;
    public final String recipientId;
    public final String advertisementId;
    public final LocalDateTime when;
    public final String content;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public MessageCreatedEvent(@JsonProperty("id") String id,
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
