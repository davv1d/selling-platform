package com.pik.handler;

import com.pik.category.query.FindCategories;
import com.pik.dto.CategoriesDtoList;
import com.pik.entity.CategoryEntity;
import com.pik.mapper.CategoryMapper;
import com.pik.repository.CategoryEntityRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryQueryHandler {
    private final CategoryEntityRepository categoryEntityRepository;
    private final CategoryMapper categoryMapper;

    @QueryHandler
    public CategoriesDtoList getCategories(FindCategories findCategories) {
        List<CategoryEntity> categories = categoryEntityRepository.findAll();
        return this.categoryMapper.mapToCategoriesDtoList(categories);
    }
}
