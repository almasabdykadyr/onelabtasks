package dev.almasabdykadyr.library.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Value("${kafka.notification.topic}")
    private String notificationKafkaTopic;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public NotificationService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(String email, String message) {
        String event = email + ":" + message;
        kafkaTemplate.send(notificationKafkaTopic, event);
    }
}