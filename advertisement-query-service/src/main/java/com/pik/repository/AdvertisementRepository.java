package com.pik.repository;

import com.pik.entity.AdvertisementEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AdvertisementRepository extends CrudRepository<AdvertisementEntity, String> {
    @Override
    List<AdvertisementEntity> findAll();

    List<AdvertisementEntity> findByCategoryId(String categoryId);

    List<AdvertisementEntity> findByUserId(String userId);
}
