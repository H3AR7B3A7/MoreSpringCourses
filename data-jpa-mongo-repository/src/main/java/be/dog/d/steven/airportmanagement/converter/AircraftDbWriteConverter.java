package be.dog.d.steven.airportmanagement.converter;

import be.dog.d.steven.airportmanagement.domain.Aircraft;
import org.springframework.core.convert.converter.Converter;

public class AircraftDbWriteConverter implements Converter<Aircraft, String> {
    @Override
    public String convert(Aircraft source) {
        return source.getModel() + "/" + source.getNbSeats();
    }
}