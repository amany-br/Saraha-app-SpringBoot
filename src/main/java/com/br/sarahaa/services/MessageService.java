package com.br.sarahaa.services;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;

import com.br.sarahaa.dto.MessageDto;
import com.br.sarahaa.exceptions.ObjectValidationException;
import com.br.sarahaa.mapper.ObjectsMapper;
import com.br.sarahaa.models.Message;
import com.br.sarahaa.models.Notification;
import com.br.sarahaa.models.User;
import com.br.sarahaa.repositories.MessageRepository;
import com.br.sarahaa.repositories.NotificationRepository;
import com.br.sarahaa.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository repository;
    private final NotificationRepository notificationRepository;
    private final ObjectsValidator<MessageDto> validator;
    private final ObjectsMapper mapper;

    public MessageDto save(MessageDto message) {
        validator.validate(message);
        if (Objects.equals(message.getSenderId(), message.getReceiverId())) {
            throw new ObjectValidationException(
                    Set.of("You cannot send a message to your self"),
                    this.getClass().getName()
            );
        }
        Message messageToSave = mapper.toMessage(message);
        messageToSave.setCreatedDate(LocalDateTime.now());
        Message savedMessage = repository.save(messageToSave);
        createAndSaveNotification(message.getReceiverId());
        return mapper.toMessageDto(savedMessage);
    }

    public List<MessageDto> findAllSentMessagesByUser(Integer userId) {
        return repository.findAllBySenderId(userId).stream()
                .map(mapper::toMessageDto)
                .collect(Collectors.toList());
    }

    public List<MessageDto> findAllReceivedMessagesByUser(Integer userId) {
        return repository.findAllByReceiverId(userId).stream()
                .map(mapper::toMessageDto)
                .collect(Collectors.toList());
    }

    public MessageDto publishMessage(Integer idMessage) {
        Message message = repository.findById(idMessage)
                .orElseThrow(EntityNotFoundException::new);
        message.setPublicMsg(true);
        Message savedMessage = repository.save(message);
        return mapper.toMessageDto(savedMessage);
    }

    public MessageDto unPublishMessage(Integer idMessage) {
        Message message = repository.findById(idMessage)
                .orElseThrow(EntityNotFoundException::new);
        message.setPublicMsg(false);
        Message savedMessage = repository.save(message);
        return mapper.toMessageDto(savedMessage);
    }

    public MessageDto markAsFavorite(Integer idMessage) {
        Message message = repository.findById(idMessage)
                .orElseThrow(EntityNotFoundException::new);
        message.setFavori(true);
        Message savedMessage = repository.save(message);
        return mapper.toMessageDto(savedMessage);
    }

    public MessageDto unmarkAsFavorite(Integer idMessage) {
        Message message = repository.findById(idMessage)
                .orElseThrow(EntityNotFoundException::new);
        message.setFavori(false);
        Message savedMessage = repository.save(message);
        return mapper.toMessageDto(savedMessage);
    }

    private void createAndSaveNotification(Integer idReceiver) {
        if (idReceiver == null) {
            return;
        }
        Notification notification = Notification.builder()
                .notification("You received a new message at " + LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .consulted(false)
                .user(User.builder()
                        .id(idReceiver)
                        .build())
                .build();
        notificationRepository.save(notification);
    }

    public List<MessageDto> findAllPublicMessages() {
        return repository.findAllByPublicMsg(true).stream()
                .map(mapper::toMessageDto)
                .collect(Collectors.toList());
    }
}