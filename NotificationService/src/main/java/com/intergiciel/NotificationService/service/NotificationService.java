package com.intergiciel.NotificationService.service;

import com.intergiciel.NotificationService.dto.NotificationDto;
import com.intergiciel.NotificationService.model.Notification;
import com.intergiciel.NotificationService.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationRepository notificationRepository;
    private final JavaMailSender mailSender;

    public NotificationService(NotificationRepository notificationRepository, JavaMailSender mailSender) {
        this.notificationRepository = notificationRepository;
        this.mailSender = mailSender;
    }

    public void sendNotification(NotificationDto dto) {
        logger.info("🔔 Notification reçue pour email : {}", dto.getToEmail());
        logger.info("Sujet : {}", dto.getSubject());
        logger.info("Corps : {}", dto.getBody());

        boolean emailSent = false;

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("naharsalman9@gmail.com");
            message.setTo(dto.getToEmail());
            message.setSubject(dto.getSubject());
            message.setText(dto.getBody());

            mailSender.send(message);
            logger.info("✅ Email envoyé avec succès à {}", dto.getToEmail());
            emailSent = true;

        } catch (Exception e) {
            logger.error("❌ Échec de l'envoi de l'e-mail à {} : {}", dto.getToEmail(), e.getMessage(), e);
        }

        try {
            Notification notification = new Notification(
                    dto.getToEmail(),
                    dto.getSubject(),
                    dto.getBody() + (emailSent ? "" : "\n\n⚠️ Envoi e-mail échoué."),
                    LocalDateTime.now()
            );
            notificationRepository.save(notification);
            logger.info("🗃️ Notification enregistrée en base pour {}", dto.getToEmail());
        } catch (Exception e) {
            logger.error("❌ Erreur lors de l'enregistrement en base : {}", e.getMessage(), e);
        }
    }
}
