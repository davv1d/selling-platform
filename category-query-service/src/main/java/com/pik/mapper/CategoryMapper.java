package com.pik.mapper;

import com.pik.dto.CategoriesDtoList;
import com.pik.dto.CategoryDto;
import com.pik.entity.CategoryEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {
    public CategoryDto mapToCategoryDto(CategoryEntity categoryEntity) {
        return new CategoryDto(categoryEntity.getId(), categoryEntity.getName());
    }

    public CategoriesDtoList mapToCategoriesDtoList(List<CategoryEntity> categories) {
        List<CategoryDto> categoriesDto = categories.stream()
                .map(this::mapToCategoryDto)
                .collect(Collectors.toList());
        return new CategoriesDtoList(categoriesDto);
    }
}
