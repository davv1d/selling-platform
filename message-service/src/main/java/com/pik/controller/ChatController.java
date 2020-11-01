package com.pik.controller;

import com.pik.domain.Message;
import com.pik.domain.dto.MessageDto;
import com.pik.repository.MessageRepository;
import com.pik.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ChatController {
    private final MessageService messageService;
    private final MessageRepository messageRepository;

    @PostMapping("/message")
    public CompletableFuture<String> sendMessage(@RequestBody MessageDto messageDto) {
        return messageService.sendMessage(messageDto);
    }

    @GetMapping("/mess/{recipientId}")
    public List<Message> get(@PathVariable String recipientId) {
        return messageRepository.findByRecipientId(recipientId);
    }
}
