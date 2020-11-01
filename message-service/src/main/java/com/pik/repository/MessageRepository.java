package com.pik.repository;

import com.pik.domain.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MessageRepository extends CrudRepository<Message, String> {
    List<Message> findByRecipientId(String recipientId);
}
