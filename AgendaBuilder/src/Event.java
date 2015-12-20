import java.util.Date;

public class Event {
	private int id;
	private String name;
	private String country;
	private String location;
	private Date startDate;
	private Date endDate;
	private Boolean isFreeEvent;
	
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

	public String toString(){
		return String.format("1. Id: %s%n"
				+ "2. Name: %s%n"
				+ "3. Country: %s%n"
				+ "4. Location: %s%n"
				+ "5. Start date: %s%n"
				+ "6. End date: %s%n"
				+ "7. Is free event: %s%n", 
				getId() , getName(), getCountry(), getLocation(), 
				getStartDate(), getEndDate(), getIsFreeEvent());
	}
}
