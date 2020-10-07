package com.pik.aggregate;

import com.pik.category.command.CreateCategoryCommand;
import com.pik.category.event.CategoryCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class CategoryAggregateTest {
    private FixtureConfiguration<CategoryAggregate> fixture;

    @Before
    public void setUp() {
        this.fixture = new AggregateTestFixture<>(CategoryAggregate.class);
    }

    @Test
    public void shouldReturnSuccessCreateCategoryCommand() {
        String id = UUID.randomUUID().toString();
        fixture.givenNoPriorActivity()
                .when(new CreateCategoryCommand(id, "test name"))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new CategoryCreatedEvent(id, "test name"));
    }
}
