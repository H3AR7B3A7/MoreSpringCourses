package com.pluralsight.springdataoverview;

//@DataJpaTest
//public class SpringDataOverviewApplicationTests {
//
//	@Autowired
//	private EntityManager entityManager;
//
//	@Test
//	public void verifyFlightCanBeSaved() {
//		final Flight flight =  new Flight();
//		flight.setOrigin("London");
//		flight.setDestination("New York");
//		flight.setScheduledAt(LocalDateTime.parse("2011-12-13T12:12:00"));
//
//		entityManager.persist(flight);
//
//		final List<Flight> flights = entityManager
//			.createQuery("SELECT f FROM Flight f", Flight.class)
//			.getResultList();
//
//		assertThat(flights)
//			.hasSize(1)
//			.first()
//			.isEqualTo(flight);
//	}
//}