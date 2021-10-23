package be.dog.d.steven.airportmanagement.converter;

import be.dog.d.steven.airportmanagement.domain.Aircraft;
import org.springframework.core.convert.converter.Converter;

public class AircraftDbReadConverter implements Converter<String, Aircraft> {
    @Override
    public Aircraft convert(String source) {
        String[] strings = source.split("/");
        return new Aircraft(strings[0], Integer.parseInt(strings[1]));
    }
}