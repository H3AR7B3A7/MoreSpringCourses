package be.steven.d.dog;

import org.springframework.context.ApplicationEvent;

public class DrivenEvent extends ApplicationEvent {
    private final String message;

    public DrivenEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}