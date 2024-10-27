package dev.almasabdykadyr.library.notification;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @KafkaListener(topics = "${kafka.notification.topic}")
    public void listen(String event) {
        String[] eventParts = event.split(":");
        String email = eventParts[0];
        String message =  eventParts[1];

        System.out.printf("sending abstract message via email %s with message %s%n", email, message);
    }
}
