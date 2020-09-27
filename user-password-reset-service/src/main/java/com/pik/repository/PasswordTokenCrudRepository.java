package com.pik.repository;

import com.pik.aggregate.PasswordToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface PasswordTokenCrudRepository extends CrudRepository<PasswordToken, String> {
    Optional<PasswordToken> findByUserId(String userId);
}

