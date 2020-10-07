package com.pik.controller;

import com.pik.sender.CategoryCommandSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class CategoryController {
    private final CategoryCommandSender categoryCommandSender;

    public CategoryController(CategoryCommandSender categoryCommandSender) {
        this.categoryCommandSender = categoryCommandSender;
    }

    @PostMapping("category/create/{name}")
    public CompletableFuture<Object> createCategory(@PathVariable String name) {
        return this.categoryCommandSender.sendCategoryCreateCommand(name);
    }
}
