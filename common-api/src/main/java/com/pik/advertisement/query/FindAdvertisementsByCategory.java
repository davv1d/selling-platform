package com.pik.advertisement.query;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FindAdvertisementsByCategory {
    public final String categoryId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FindAdvertisementsByCategory(@JsonProperty("categoryId") String categoryId) {
        this.categoryId = categoryId;
    }
}
