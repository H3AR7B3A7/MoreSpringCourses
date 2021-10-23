package be.dog.d.steven.airportmanagement;

import be.dog.d.steven.airportmanagement.queries.FlightInformationQueries;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class QueriesTest {
    @Autowired
    private FlightInformationQueries queries;

    @Test
    void getDestinationFieldTest() {
        assertThat(queries.getDepartureCityForId("61742ba83c581136098a3bb8")).isEqualTo("New York");
    }
}