package com.intergiciel.NotificationService.controller;

import com.intergiciel.NotificationService.dto.NotificationDto;
import com.intergiciel.NotificationService.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<String> sendNotification(@RequestBody NotificationDto notificationDto) {
        notificationService.sendNotification(notificationDto);
        return ResponseEntity.ok("Notification enregistrée et envoyée avec succès.");
    }
}


