package dev.almasabdykadyr.library.service;

import dev.almasabdykadyr.library.notification.NotificationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationProducer notificationProducer;
    private final UserService userService;

    public void sendRentCreatedNotification(Long userId) {
        var user = userService.getUserById(userId);
        notificationProducer.sendNotification(user.getEmail(), "rent for user %s created".formatted(user.getEmail()));
    }

    public void sendRentReturnedNotification(Long userId) {
        var user = userService.getUserById(userId);
        notificationProducer.sendNotification(user.getEmail(), "rent for user %s returned".formatted(user.getEmail()));
    }
}
