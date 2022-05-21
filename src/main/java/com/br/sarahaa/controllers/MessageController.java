package com.br.sarahaa.controllers;

import com.br.sarahaa.dto.MessageDto;
import com.br.sarahaa.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService service;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageDto> save(@RequestBody MessageDto message) {
        return ResponseEntity.ok(service.save(message));
    }

    @GetMapping("/sent/{user-id}")
    public ResponseEntity<List<MessageDto>> findAllSentMessagesByUser(
            @PathVariable(name = "user-id") Integer userId
    ) {
        return ResponseEntity.ok(service.findAllSentMessagesByUser(userId));
    }

    @GetMapping("/received/{user-id}")
    public ResponseEntity<List<MessageDto>> findAllReceivedMessagesByUser(
            @PathVariable(name = "user-id") Integer userId
    ) {
        return ResponseEntity.ok(service.findAllReceivedMessagesByUser(userId));
    }

    @PatchMapping("/publish/{id-message}")
    public ResponseEntity<MessageDto> publishMessage(
            @PathVariable(name = "id-message") Integer idMessage
    ) {
        return ResponseEntity.ok(service.publishMessage(idMessage));
    }

    @PatchMapping("/unpublish/{id-message}")
    public ResponseEntity<MessageDto> unPublishMessage(
            @PathVariable(name = "id-message") Integer idMessage
    ) {
        return ResponseEntity.ok(service.unPublishMessage(idMessage));
    }

    @PatchMapping("/mark-as-favorite/{id-message}")
    public ResponseEntity<MessageDto> markAsFavorite(
            @PathVariable(name = "id-message") Integer idMessage
    ) {
        return ResponseEntity.ok(service.markAsFavorite(idMessage));
    }

    @PatchMapping("/unmark-as-favorite/{id-message}")
    public ResponseEntity<MessageDto> unmarkAsFavorite(
            @PathVariable(name = "id-message") Integer idMessage
    ) {
        return ResponseEntity.ok(service.unmarkAsFavorite(idMessage));
    }

    @GetMapping("/alli/public")
    public ResponseEntity<List<MessageDto>> findAllPublicMessages() {
        return ResponseEntity.ok(service.findAllPublicMessages());
    }

}
