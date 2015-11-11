import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgendaBuilderEvent {
	private int id;
	private String name;
	private String country;
	private String location;
	private Date startDate;
	private Date endDate;
	private Boolean isFreeEvent;
	
	public AgendaBuilderEvent(){
		this.id = 0;
		this.name = null;
		this.country = null;
		this.startDate = null;
		this.endDate = null;
		this.isFreeEvent = true;
	}

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

	public void setStartDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.startDate = sdf.parse(date);
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.endDate = sdf.parse(date);
	}

	public Boolean getIsFreeEvent() {
		return isFreeEvent;
	}

	public void setIsFreeEvent(Boolean isFreeEvent) {
		this.isFreeEvent = isFreeEvent;
	}

	public String toString(){
		return String.format("Id: %s%n"
				+ "Name: %s%n"
				+ "Country: %s%n"
				+ "Location: %s%n"
				+ "Start date: %s%n"
				+ "End date: %s%n"
				+ "Is free event: %s%n", 
				getId() , getName(), getCountry(), getLocation(), 
				getStartDate(), getEndDate(), getIsFreeEvent());
	}
}
