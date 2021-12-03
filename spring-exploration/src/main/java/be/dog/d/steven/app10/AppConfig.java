package be.dog.d.steven.app10;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("be.dog.d.steven.app10")
public class AppConfig {
    
    @Bean
    @SalaryScope
    public Sal salary() {
        return new Salary();
    }
}