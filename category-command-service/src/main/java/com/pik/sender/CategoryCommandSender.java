package com.pik.sender;

import com.pik.category.command.CreateCategoryCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class CategoryCommandSender {
    private final CommandGateway commandGateway;
    private final Logger logger = LoggerFactory.getLogger(CategoryCommandSender.class);

    public CategoryCommandSender(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<Object> sendCategoryCreateCommand(String categoryName) {
        String id = UUID.randomUUID().toString();
        CreateCategoryCommand command = new CreateCategoryCommand(id, categoryName);
        this.logger.info("Sent create category command " + id + " | " + categoryName);
        return this.commandGateway.send(command);
    }
}
