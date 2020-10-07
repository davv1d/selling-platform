package com.pik.repository;

import com.pik.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CategoryEntityRepository extends CrudRepository<CategoryEntity, String> {
    @Override
    List<CategoryEntity> findAll();
}
