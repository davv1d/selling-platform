package com.pik.handler;

import com.pik.category.event.CategoryCreatedEvent;
import com.pik.entity.CategoryEntity;
import com.pik.repository.CategoryEntityRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryEventHandler {
    private final CategoryEntityRepository categoryEntityRepository;

    @EventHandler
    public void createCategory(CategoryCreatedEvent event) {
        System.out.println("create");
        CategoryEntity categoryEntity = new CategoryEntity(event.id, event.name);
        categoryEntityRepository.save(categoryEntity);
    }
}
