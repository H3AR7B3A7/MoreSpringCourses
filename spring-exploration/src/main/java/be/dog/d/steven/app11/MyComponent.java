package be.dog.d.steven.app11;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class MyComponent {

    public MyComponent() {
        System.out.println("MY COMPONENT CREATED !!!");
    }

    @PostConstruct
    public void doSomething() {
        System.out.println("POST CONSTRUCT CALLED...");
    }
    
    @PreDestroy
    public void doSomethingElse() {
        System.out.println("PRE DESTROY CALLED...");
    }
}