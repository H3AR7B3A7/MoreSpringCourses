package com.pluralsight.springaop.flightsmanagement.domain;

import com.pluralsight.springaop.flightsmanagement.dao.PassengerDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FlightsManagement {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("flightsmanagement/aop.xml");
		
		Flight flight = (Flight) context.getBean("flight");
		
		flight.print();
		System.out.println(flight.getId());
		flight.setId("AA5678");

		System.out.println(flight.getCompany());
		
		for (Passenger passenger : flight.getPassengers()) {
			System.out.println(passenger.getName());
			passenger.print();
		}
		
		Ticket ticket = (Ticket) context.getBean("ticket");
		ticket.setNumber("0987654321");

		PassengerDao passengerDao = (PassengerDao) context.getBean("passengerDaoImpl");
		passengerDao.getPassenger(1);
		passengerDao.getPassenger(1);

		context.close();
	}
}