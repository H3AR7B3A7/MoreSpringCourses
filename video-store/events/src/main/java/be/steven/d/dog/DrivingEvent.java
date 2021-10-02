package be.steven.d.dog;

import org.springframework.context.ApplicationEvent;

public class DrivingEvent extends ApplicationEvent {
    private final String message;

    public DrivingEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}