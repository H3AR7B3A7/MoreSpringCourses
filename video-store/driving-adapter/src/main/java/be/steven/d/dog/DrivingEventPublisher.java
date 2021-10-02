package be.steven.d.dog;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class DrivingEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishMyEvent(final String message) {
        log.warn("Publishing event: {}", message);
        DrivingEvent drivingEvent = new DrivingEvent(this, message);
        applicationEventPublisher.publishEvent(drivingEvent);
    }
}