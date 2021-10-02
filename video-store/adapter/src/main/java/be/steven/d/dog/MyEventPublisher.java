package be.steven.d.dog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public MyEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishMyEvent(final String message) {
        log.warn("Publishing event: {}", message);
        MyEvent myEvent = new MyEvent(this, message);
        applicationEventPublisher.publishEvent(myEvent);
    }
}