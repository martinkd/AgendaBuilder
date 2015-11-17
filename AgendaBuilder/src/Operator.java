import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Operator {
	private static Map<Integer, Event> listOfEvents = new HashMap<Integer, Event>();

	public static void createNewEvent() {
		Scanner input = new Scanner(System.in);
		Event newEvent = new Event();
		System.out.println("Enter Id: ");
		newEvent.setId(getNextInt(input));
		System.out.print("Enter name: ");
		newEvent.setName(input.next());
		System.out.print("Enter country: ");
		newEvent.setCountry(input.next());
		System.out.print("Enter location: ");
		newEvent.setLocation(input.next());
		setStartDate(input, newEvent);
		setEndDate(input, newEvent);
		setIsFreeEvent(newEvent);
		System.out.println("You sucessfully created new event\n");
		AgendaBuilderMenu.operatorMenu();
	}

	private static int getNextInt(Scanner input) {
		while (true) {
			try {
				String txtNum = input.nextLine();
				return Integer.parseInt(txtNum);
			} catch (NumberFormatException e) {
				System.err.println("Enter valid number");
			}
		}
	}

	private static void setIsFreeEvent(Event newEvent) {
		System.out.print("Is free event(true/false): ");
		try {
			Scanner scan = new Scanner(System.in);
			newEvent.setIsFreeEvent(scan.nextBoolean());
			scan.close();
		} catch (InputMismatchException e) {
			System.err.println("Enter \"true\" or \"false\"");
			setIsFreeEvent(newEvent);
		}
	}

	private static void setStartDate(Scanner input, Event newEvent) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		System.out.print("Enter start date(dd.mm.yyyy): ");
		try {
			newEvent.setStartDate(sdf.parse(input.next()));
		} catch (ParseException e) {
			System.err.println("Enter valid date format (dd.mm.yyyy)!");
			setStartDate(input, newEvent);
		}
	}

	private static void setEndDate(Scanner input, Event newEvent) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		System.out.print("Enter end date(dd.mm.yyyy): ");
		try {
			newEvent.setEndDate(sdf.parse(input.next()));
		} catch (ParseException e) {
			System.err.println("Enter valid date format (dd.mm.yyyy)!");
			setEndDate(input, newEvent);
		}
	}

	public static void showAllEvents() {
		for (Entry<Integer, Event> event : listOfEvents.entrySet()) {
			System.out.printf("Id: %s, Name: %s", event.getValue().getId(), event.getValue().getName());
		}
	}

	public void readEvent(int id) {
		System.out.println(listOfEvents.get(id));
	}

	public void editEvent(int id) {
		if (listOfEvents.containsKey(id)) {
		}
	}
}
