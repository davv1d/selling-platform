package com.pik.mapper;

import com.pik.chat.command.CreateMessageCommand;
import com.pik.domain.dto.MessageDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class MessageMapper {
    public CreateMessageCommand mapToCreateMessageCommand(MessageDto messageDto) {
        return new CreateMessageCommand(
                UUID.randomUUID().toString(),
                messageDto.getSenderId(),
                messageDto.getRecipientId(),
                messageDto.getAdvertisementId(),
                LocalDateTime.now(),
                messageDto.getContent());
    }
}
