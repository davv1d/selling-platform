package com.pik.email.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class SendEmailRemindPasswordCommand {
    @TargetAggregateIdentifier
    public final String id;
    public final String email;
    public final String reminderCode;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public SendEmailRemindPasswordCommand(@JsonProperty("id") String id,
                                          @JsonProperty("email") String email,
                                          @JsonProperty("reminderCode") String reminderCode) {
        this.id = id;
        this.email = email;
        this.reminderCode = reminderCode;
    }
}
