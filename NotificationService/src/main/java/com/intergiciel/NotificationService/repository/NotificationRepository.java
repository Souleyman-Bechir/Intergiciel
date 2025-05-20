package com.intergiciel.NotificationService.repository;

import com.intergiciel.NotificationService.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
