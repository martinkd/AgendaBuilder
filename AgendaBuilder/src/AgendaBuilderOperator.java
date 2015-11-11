import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class AgendaBuilderOperator {
	private Map<Integer, AgendaBuilderEvent> listOfEvents = new HashMap<Integer, AgendaBuilderEvent>();
	
	public AgendaBuilderEvent createEvent(int id, String name, String country, String location, 
			String startDate, String endDate, Boolean  isFreeEvent) throws ParseException{
		AgendaBuilderEvent newEvent = new AgendaBuilderEvent();
		newEvent.setId(id);
		newEvent.setName(name);
		newEvent.setCountry(country);
		newEvent.setLocation(location);
		newEvent.setStartDate(startDate);
		newEvent.setEndDate(endDate);
		newEvent.setIsFreeEvent(isFreeEvent);
		listOfEvents.put(id, newEvent);
		return newEvent;
	}
	
	public void showAllEvents(){
		for (Entry<Integer, AgendaBuilderEvent> event : listOfEvents.entrySet()) {
			System.out.println(event.getValue());
		}
	}
	
	public void readEvent(int id){
		System.out.println(listOfEvents.get(id));
	}
	
	public void editEvent(int id){
		if (listOfEvents.containsKey(id)){
			
		}
	}
}
