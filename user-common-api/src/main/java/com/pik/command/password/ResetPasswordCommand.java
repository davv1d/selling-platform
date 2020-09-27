package com.pik.command.password;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.Instant;

public class ResetPasswordCommand {
    @TargetAggregateIdentifier
    public final String id;
    public final String code;
    public final String newPassword;
    public final Instant when;


    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ResetPasswordCommand(@JsonProperty("id") String id,
                                @JsonProperty("code") String code,
                                @JsonProperty("newPassword") String newPassword,
                                @JsonProperty("when") Instant when) {
        this.id = id;
        this.code = code;
        this.newPassword = newPassword;
        this.when = when;
    }
}
