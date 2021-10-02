package be.steven.d.dog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DrivenEventListener implements ApplicationListener<DrivenEvent> {

    @Override
    public void onApplicationEvent(DrivenEvent event) {
        log.warn("I am driven by a driving event...");
    }
}