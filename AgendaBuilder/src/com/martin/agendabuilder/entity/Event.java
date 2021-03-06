package com.martin.agendabuilder.entity;

import java.util.Date;

public class Event {
	
	public Event () {};
	
	public Event(int id, String name, String country, String location, Date startDate, Date endDate,
			Boolean isFreeEvent) {
		this.id = id;
		this.name = name;
		this.country = country;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isFreeEvent = isFreeEvent;
	}

	private int id;
	private String name = "";
	private String country = null;
	private String location = null;
	private Date startDate = null;
	private Date endDate = null;
	private Boolean isFreeEvent = false;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date date) {
		this.startDate = date;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date date) {
		this.endDate = date;
	}

	public Boolean getIsFreeEvent() {
		return isFreeEvent;
	}

	public void setIsFreeEvent(Boolean isFreeEvent) {
		this.isFreeEvent = isFreeEvent;
	}

	public String toString() {
		return String.format(
				"\tId: %s%n" + "1. Name: %s%n" + "2. Country: %s%n" + "3. Location: %s%n" + "4. Start date: %s%n"
						+ "5. End date: %s%n" + "6. Is free event: %s%n",
				getId(), getName(), getCountry(), getLocation(), getStartDate(), getEndDate(), getIsFreeEvent());
	}
}
