package be.steven.d.dog;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class DrivingEventListener implements ApplicationListener<DrivingEvent> {
    private DrivenEventPublisher drivenEventPublisher;
    
    @Override
    public void onApplicationEvent(DrivingEvent event) {
        log.warn("Event received: {}", event.getMessage());
        drivenEventPublisher.publishDrivenEvent("This is a driven event...");
    }
}