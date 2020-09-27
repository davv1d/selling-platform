package com.pik.repository;

import com.pik.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, String> {
    @Override
    List<User> findAll();

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndStatus(String email, String status);
}

