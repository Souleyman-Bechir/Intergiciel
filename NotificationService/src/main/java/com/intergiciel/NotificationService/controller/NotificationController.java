package com.intergiciel.NotificationService.controller;

import com.intergiciel.NotificationService.dto.NotificationDto;
import com.intergiciel.NotificationService.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<String> sendNotification(@RequestBody NotificationDto notificationDto) {
        logger.info("📩 Requête reçue pour envoyer une notification à : {}", notificationDto.getToEmail());

        try {
            notificationService.sendNotification(notificationDto);
            return ResponseEntity.ok("✅ Notification enregistrée et envoyée avec succès.");
        } catch (Exception e) {
            logger.error("❌ Erreur lors de l'envoi de la notification : {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Erreur serveur : échec de l'envoi de la notification.");
        }
    }
}
