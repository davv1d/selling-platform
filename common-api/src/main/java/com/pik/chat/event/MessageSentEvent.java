package com.pik.chat.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageSentEvent {
    public final String id;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public MessageSentEvent(@JsonProperty("id") String id) {
        this.id = id;
    }
}
