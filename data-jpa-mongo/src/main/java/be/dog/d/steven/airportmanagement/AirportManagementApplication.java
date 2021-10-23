package be.dog.d.steven.airportmanagement;

import be.dog.d.steven.airportmanagement.converter.AircraftDbReadConverter;
import be.dog.d.steven.airportmanagement.converter.AircraftDbWriteConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AirportManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirportManagementApplication.class, args);
    }
    
    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new AircraftDbReadConverter());
        converters.add(new AircraftDbWriteConverter());
        return new MongoCustomConversions(converters);
    }
}