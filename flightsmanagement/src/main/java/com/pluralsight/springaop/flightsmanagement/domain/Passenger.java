package com.pluralsight.springaop.flightsmanagement.domain;

public class Passenger {
	private String name;
	private String country;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void print() {
		System.out.println(this);
	}

	public String toString(){
		return "Passenger " + name + " from " + country;
	}
}