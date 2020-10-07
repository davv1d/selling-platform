package com.pik.category.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateCategoryCommand {
    @TargetAggregateIdentifier
    public final String id;
    public final String name;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CreateCategoryCommand(@JsonProperty("id") String id,
                                @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
}
