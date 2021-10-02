package be.steven.d.dog;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class DrivenEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishDrivenEvent(String message){
        log.warn("Publishing event: {}", message);
        DrivenEvent drivenEvent = new DrivenEvent(this, message);
        applicationEventPublisher.publishEvent(drivenEvent);
    }
}