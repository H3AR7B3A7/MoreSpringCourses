package be.dog.d.steven.app12;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "be.dog.d.steven.app12")
public class AppConfig {
    @Bean(initMethod = "myOtherInitMethod")
    public MyBean myBean() {
        return new MyBean();
    }
}