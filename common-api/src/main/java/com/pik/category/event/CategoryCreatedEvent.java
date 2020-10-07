package com.pik.category.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryCreatedEvent {
    public final String id;
    public final String name;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CategoryCreatedEvent(@JsonProperty("id") String id,
                                @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
}
