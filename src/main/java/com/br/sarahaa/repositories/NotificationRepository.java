package com.br.sarahaa.repositories;

import com.br.sarahaa.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findAllByUserId(Integer userId);
}
