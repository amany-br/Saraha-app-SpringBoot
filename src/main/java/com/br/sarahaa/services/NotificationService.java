package com.br.sarahaa.services;

import java.util.List;
import java.util.stream.Collectors;

import com.br.sarahaa.dto.NotificationDto;
import com.br.sarahaa.mapper.ObjectsMapper;
import com.br.sarahaa.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;
    private ObjectsMapper mapper;

    public List<NotificationDto> findAllNotificationsByUser(Integer userId) {
        return repository.findAllByUserId(userId).stream()
                .map(mapper::toNotificationDto)
                .collect(Collectors.toList());
    }

}