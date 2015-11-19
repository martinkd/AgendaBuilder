import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Operator {
	private static Map<Integer, Event> listOfEvents = new HashMap<Integer, Event>();

	public static void createNewEvent(Scanner input) {
		Event newEvent = new Event();
		System.out.print("Id: ");
		int id = getValidInteger(input);
		if (!listOfEvents.containsKey(id)) {
			newEvent.setId(id);
		} else {
			System.err.printf("Event with Id: %s already exists, please enter another Id%n", id);
			System.out.println();
			createNewEvent(input);
		}

		System.out.print("Enter name: ");
		String name = input.nextLine();
		newEvent.setName(name);
		System.out.print("Enter country: ");
		newEvent.setCountry(input.nextLine());
		System.out.print("Enter location: ");
		newEvent.setLocation(input.nextLine());
		System.out.print("Enter start date: ");
		newEvent.setStartDate(validDate(input));
		System.out.print("Enter end date: ");
		newEvent.setEndDate(validDate(input));
		System.out.print("Enter \"true\" if the event is free: ");
		newEvent.setIsFreeEvent(getValidBoolean(input));
		listOfEvents.put(id, newEvent);
		System.out.printf("%nYou sucsesfully created event \"%s\" %n", name);
		System.out.println();
	}
	
	private static int getValidInteger(Scanner input) {
		while (true) {
			try {
				String txtNum = input.nextLine();
				return Integer.parseInt(txtNum);
			} catch (NumberFormatException e) {
				System.err.println("Enter valid number");
			}
		}
	}
	
	private static Date validDate(Scanner input) {
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		dateFormat.setLenient(false);
		String dateAsString = input.nextLine();
		try {
			Date date = dateFormat.parse(dateAsString);
			return date;
		} catch (ParseException e) {
			System.err.println("Enter valid date format (dd.mm.yyyy)!");
			return validDate(input);
		}
	}
	
	private static boolean getValidBoolean(Scanner input) {
		while (true) {
			try {
				String txtNum = input.nextLine();
				return Boolean.parseBoolean(txtNum);
			} catch (NumberFormatException e) {
				System.err.println("Enter valid number");
			}
		}
	}
	
	public static void showAllEvents() {
		for (Entry<Integer, Event> event : listOfEvents.entrySet()) {
			System.out.printf("Id: %s, Name: %s", event.getValue().getId(), event.getValue().getName());
			System.out.println();
		}
	}
	
	public static void readEvent(Scanner input) {
		System.out.println(listOfEvents.get(getValidInteger(input)));
	}
}
