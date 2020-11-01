package com.pik.service;

import com.pik.chat.command.CreateMessageCommand;
import com.pik.domain.dto.MessageDto;
import com.pik.mapper.MessageMapper;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageMapper messageMapper;
    private final CommandGateway commandGateway;

    public CompletableFuture<String> sendMessage(MessageDto messageDto) {
        CreateMessageCommand command = messageMapper.mapToCreateMessageCommand(messageDto);
        return commandGateway.send(command);
    }
}
