package be.dog.d.steven.app13;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean(destroyMethod = "myOtherDestroy")
    public MyBean myBean() {
        return new MyBean();
    }
}