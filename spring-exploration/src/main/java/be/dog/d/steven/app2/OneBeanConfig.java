package be.dog.d.steven.app2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OneBeanConfig {
    
    @Bean
    SimpleBean simpleBean() {
        return new SimpleBeanImpl();
    }
}