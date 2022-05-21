package com.br.sarahaa.mapper;

import com.br.sarahaa.dto.MessageDto;
import com.br.sarahaa.dto.NotificationDto;
import com.br.sarahaa.dto.UserDto;
import com.br.sarahaa.models.Message;
import com.br.sarahaa.models.Notification;
import com.br.sarahaa.models.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ObjectsMapper {

    public Message toMessage(MessageDto messageDto) {
        if (messageDto == null) {
            return null;
        }
        return Message.builder()
                .id(messageDto.getId())
                .content(messageDto.getContent())
                .favori(messageDto.isFavori())
                .publicMsg(messageDto.isPublicMsg())
                .typeMsg(messageDto.getTypeMsg())
                .receiver(
                        User.builder()
                                .id(messageDto.getReceiverId())
                                .build()
                )
                .sender(
                        messageDto.getSenderId() != null ?
                                User.builder()
                                        .id(messageDto.getSenderId())
                                        .build() : null
                )
                .build();
    }

    public MessageDto toMessageDto(Message message) {
        if (message == null) {
            return null;
        }
        return MessageDto.builder()
                .id(message.getId())
                .content(message.getContent())
                .createdDate(message.getCreatedDate())
                .favori(message.isFavori())
                .publicMsg(message.isPublicMsg())
                .typeMsg(message.getTypeMsg())
                .senderId(
                        message.getSender() != null ? message.getSender().getId() : null
                )
                .receiverId(message.getReceiver().getId())
                .build();
    }

    public User toUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .birthDate(userDto.getBirthDate())
                .email(userDto.getEmail())
                .userName(userDto.getUserName())
                .password(userDto.getPassword())
                .joiningDate(LocalDateTime.now())
                .build();
    }

    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .userName(user.getUserName())
                .build();
    }

    public NotificationDto toNotificationDto(Notification notification) {
        if (notification == null) {
            return  null;
        }
        return NotificationDto.builder()
                .id(notification.getId())
                .notification(notification.getNotification())
                .createdDate(notification.getCreatedDate())
                .consulted(notification.isConsulted())
                .build();
    }
}
