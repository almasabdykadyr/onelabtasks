package dev.almasabdykadyr.library.aop;

import dev.almasabdykadyr.library.dto.NewRentalRequest;
import dev.almasabdykadyr.library.entity.Rental;
import dev.almasabdykadyr.library.repo.RentalRepository;
import dev.almasabdykadyr.library.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class NotificationAspect {

    private final NotificationService notificationService;
    private final RentalRepository rentalRepository;

    @Pointcut("execution(* dev.almasabdykadyr.library.service.RentalService.rent(..)) ")
    public void rentMethod() {}

    @AfterReturning("rentMethod()")
    public void sendRentCreatedNotification(JoinPoint joinPoint) {
        NewRentalRequest request = (NewRentalRequest) joinPoint.getArgs()[0];

        notificationService.sendRentCreatedNotification(request.userId());
    }

    @Pointcut("execution(* dev.almasabdykadyr.library.service.RentalService.returnRent(..))")
    public void returnRentMethod() {}

    @AfterReturning("returnRentMethod()")
    public void sendRentReturnedNotification(JoinPoint joinPoint) {
        Long rentId = (Long) joinPoint.getArgs()[0];
        Rental rent = rentalRepository.findById(rentId).get();

        notificationService.sendRentReturnedNotification(rent.getUserId());
    }
}
