package be.dog.d.steven.app11;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
@ComponentScan(basePackages = "be.dog.d.steven.app11")
public class AppConfig {

    public AppConfig() {
        System.out.println("APP CONFIG CREATED !!!");
    }
    
    @PostConstruct
    public void init() {
        System.out.println("POST CONSTRUCT IN APP CONFIG CALLED...");
    }
    
    @PreDestroy
    public void destroy() {
        System.out.println("PRE DESTROY IN APP CONFIG CALLED...");
    }
}