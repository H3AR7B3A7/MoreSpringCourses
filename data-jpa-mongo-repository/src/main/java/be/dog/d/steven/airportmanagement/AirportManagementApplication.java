package be.dog.d.steven.airportmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
@ConfigurationProperties(prefix = "airport")
public class AirportManagementApplication {
    
    private Map<String, String> seeding;

    public Map<String, String> getSeeding() {
        return seeding;
    }

    public void setSeeding(Map<String, String> seeding) {
        this.seeding = seeding;
    }

    public static void main(String[] args) {
        SpringApplication.run(AirportManagementApplication.class, args);
    }
    
    @Bean
    public void test() {
        System.out.println("Seeding Enabled:" + getSeeding().get("enabled"));
    }
}