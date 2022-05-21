package com.br.sarahaa.controllers;

import java.util.List;

import com.br.sarahaa.dto.NotificationDto;
import com.br.sarahaa.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @GetMapping("/{user-id}")
    public ResponseEntity<List<NotificationDto>> findALlNotificationsByUser(
            @PathVariable("user-id") Integer userId
    ) {
        return ResponseEntity.ok(service.findAllNotificationsByUser(userId));
    }
}