package com.pluralsight.springdataoverview;

import static org.assertj.core.api.Assertions.assertThat;

import com.pluralsight.springdataoverview.entity.Flight;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SpringDataOverviewApplicationTests {

	@Autowired
	private EntityManager entityManager;

	@Test
	public void verifyFlighTCanBeSaved() {
		final Flight flight =  new Flight();
		flight.setOrigin("London");
		flight.setDestination("New York");
		flight.setScheduledAt(LocalDateTime.parse("2011-12-13T12:12:00"));

		entityManager.persist(flight);

		final List<Flight> flights = entityManager
			.createQuery("SELECT f FROM Flight f", Flight.class)
			.getResultList();

		assertThat(flights)
			.hasSize(1)
			.first()
			.isEqualTo(flight);
	}

}
