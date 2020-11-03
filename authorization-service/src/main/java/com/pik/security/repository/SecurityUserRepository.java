package com.pik.security.repository;

import com.pik.domain.SecurityUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface SecurityUserRepository extends CrudRepository<SecurityUser, String> {
    Optional<SecurityUser> findByEmail(String email);
}
