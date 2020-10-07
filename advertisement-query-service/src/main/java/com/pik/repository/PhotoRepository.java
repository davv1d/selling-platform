package com.pik.repository;

import com.pik.entity.PhotoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PhotoRepository extends CrudRepository<PhotoEntity, String> {
}
