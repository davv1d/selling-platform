package com.pik.aggregate;

import com.pik.category.command.CreateCategoryCommand;
import com.pik.category.event.CategoryCreatedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@NoArgsConstructor
@Getter
@Aggregate
public class CategoryAggregate {
    @AggregateIdentifier
    private String id;
    private String name;

    @CommandHandler
    public CategoryAggregate(CreateCategoryCommand command) {
        AggregateLifecycle.apply(new CategoryCreatedEvent(command.id, command.name));
    }

    @EventSourcingHandler
    public void on(CategoryCreatedEvent event) {
        this.id = event.id;
        this.name = event.name;
    }
}
