package com.pik.controller;

import com.pik.category.query.FindCategories;
import com.pik.dto.CategoriesDtoList;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CategoryQueryController {
    private final QueryGateway queryGateway;

    @GetMapping("/")
    public CompletableFuture<CategoriesDtoList> getCategories() {
        return this.queryGateway.query(new FindCategories(), ResponseTypes.instanceOf(CategoriesDtoList.class));
    }
}
