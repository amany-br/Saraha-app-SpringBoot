package com.br.sarahaa.repositories;

import com.br.sarahaa.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAllBySenderId(Integer sender);

    List<Message> findAllByReceiverId(Integer id);

    List<Message> findAllByPublicMsg(boolean isPublic);
}
