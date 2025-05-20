package com.intergiciel.NotificationService.service;

import com.intergiciel.NotificationService.dto.NotificationDto;
import com.intergiciel.NotificationService.model.Notification;  
import com.intergiciel.NotificationService.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void sendNotification(NotificationDto dto) {
        Notification notification = new Notification(
            dto.getToEmail(),
            dto.getSubject(),
            dto.getBody(),
            LocalDateTime.now()  // ajout du timestamp
        );
        notificationRepository.save(notification);
        System.out.println("Notification enregistrée et envoyée à : " + dto.getToEmail());
    }
}


