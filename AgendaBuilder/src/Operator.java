import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Operator {
	private static final int ID = 1;
	private static final int NAME = 2;
	private static final int COUNTRY = 3;
	private static final int LOCATION = 4;
	private static final int START_DATE = 5;
	private static final int END_DATE = 6;
	private static final int IS_FREE = 7;
	private static final int RETURN = 0;

	public static Map<Integer, Event> listOfEvents = new HashMap<Integer, Event>();

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
		newEvent.setName(input.nextLine());
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
		System.out.printf("%nYou sucsesfully created event with id: \"%s\" %n", id);
		System.out.println();
		AgendaBuilderMenu.operatorMenu();
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
		while (true) {
			DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			dateFormat.setLenient(false);
			String txtDate = input.nextLine();
			try {
				Date date = dateFormat.parse(txtDate);
				return date;
			} catch (ParseException e) {
				System.err.println("Enter valid date format (dd.mm.yyyy)!");
			}
		}
	}

	private static boolean getValidBoolean(Scanner input) {
		while (true) {
			try {
				String txtBoolean = input.nextLine();
				return Boolean.parseBoolean(txtBoolean);
			} catch (NumberFormatException e) {
				System.err.println("Enter valid boolean");
			}
		}
	}

	public static void showAllEvents(Scanner input) {
		if (listOfEvents.isEmpty()) {
			System.out.println("There are no events to show");
			System.out.println();
			AgendaBuilderMenu.readEventMenu();
		} else {
			for (Entry<Integer, Event> event : listOfEvents.entrySet()) {
				System.out.printf("Id: %s, Name: %s%n", event.getValue().getId(), event.getValue().getName());
				System.out.println();
			}
			while (true) {
				System.out.println("Press \"0\" (zero) to return");
				if (getValidInteger(input) == RETURN) {
					AgendaBuilderMenu.readEventMenu();
				}
			}
		}
	}

	public static void readEvent(Scanner input) {
		if (listOfEvents.isEmpty()) {
			System.out.println("There are no events to show");
			System.out.println();
			AgendaBuilderMenu.readEventMenu();
		} else {
			System.out.print("Enter Id of the event you want to read: ");
			int id = getValidInteger(input);
			System.out.println();
			if (listOfEvents.containsKey(id)) {
				System.out.println(listOfEvents.get(id));
				while (true) {
					System.out.println("Press \"0\" (zero) to return");
					if (getValidInteger(input) == RETURN) {
						AgendaBuilderMenu.readEventMenu();
					}
				}
			} else {
				System.out.printf("Event with Id \"%s\" does not exists%n", id);
				System.out.println();
				AgendaBuilderMenu.readEventMenu();
			}
		}
	}

	public static void editEvent(Scanner input) {
		if (listOfEvents.isEmpty()) {
			System.out.println("There are no events to edit");
			System.out.println();
			AgendaBuilderMenu.editEventMenu();
		} else {
			System.out.print("Enter Id of the event you want to edit: ");
			int id = getValidInteger(input);
			System.out.println();
			if (listOfEvents.containsKey(id)) {
				chooseDataToEdit(input, id);
			} else {
				System.out.printf("Event with Id \"%s\" does not exists%n", id);
				System.out.println();
				AgendaBuilderMenu.editEventMenu();
			}
		}
	}

	private static void chooseDataToEdit(Scanner input, int id) {
		System.out.println(listOfEvents.get(id));
		System.out.print("Choose data to edit (1-7) or 0 to return: ");
		switch (getValidInteger(input)) {
		case RETURN:
			AgendaBuilderMenu.editEventMenu();
			break;
		case ID:
			setNewId(input, id);
			chooseDataToEdit(input, id);
			break;
		case NAME:
			System.out.println("Enter new name: ");
			String newName = input.nextLine();
			listOfEvents.get(id).setName(newName);
			System.out.println("You sucessfully changed name of the event to: " + newName);
			chooseDataToEdit(input, id);
			break;
		case COUNTRY:
			System.out.println("Enter new country: ");
			String newCountry = input.nextLine();
			listOfEvents.get(id).setCountry(newCountry);
			System.out.println("You sucessfully changed country of the event to: " + newCountry);
			chooseDataToEdit(input, id);
			break;
		case LOCATION:
			System.out.println("Enter new location: ");
			String newLocation = input.nextLine();
			listOfEvents.get(id).setLocation(newLocation);
			System.out.println("You sucessfully changed location of the event to: " + newLocation);
			chooseDataToEdit(input, id);
			break;
		case START_DATE:
			System.out.println("Enter new start date: ");
			Date newStartDate = validDate(input);
			listOfEvents.get(id).setStartDate(newStartDate);
			System.out.println("You sucessfully changed start date of the event to: " + newStartDate);
			chooseDataToEdit(input, id);
			break;
		case END_DATE:
			System.out.println("Enter new end date: ");
			Date newEndDate = validDate(input);
			listOfEvents.get(id).setEndDate(newEndDate);
			System.out.println("You sucessfully changed start date of the event to: " + newEndDate);
			chooseDataToEdit(input, id);
			break;
		case IS_FREE:
			System.out.println("If you want to make the event free, enter \"true\": ");
			Boolean isFree = getValidBoolean(input);
			listOfEvents.get(id).setIsFreeEvent(isFree);
			if (isFree) {
				System.out.println("You set the event free");
				chooseDataToEdit(input, id);
				break;
			} else {
				System.out.println("You set the event not free");
				chooseDataToEdit(input, id);
				break;
			}
		default:
			System.err.println("Enter valid number");
			chooseDataToEdit(input, id);
			break;
		}
	}

	private static void setNewId(Scanner input, int id) {
		System.out.print("Enter new Id: ");
		int newId = getValidInteger(input);
		System.out.println();
		if (listOfEvents.containsKey(newId)) {
			System.out.printf("Cannot change the Id to \"%s\". Event with that Id already exists%n", newId);
			System.out.println();
			chooseDataToEdit(input, id);
		} else {
			Event currentEvent = listOfEvents.remove(id);
			currentEvent.setId(newId);
			listOfEvents.put(newId, currentEvent);
			System.out.printf("You sucessfully changed Id of the event from \"%s\" to \"%s\"%n", id, newId);
			System.out.println();
			chooseDataToEdit(input, newId);
		}
	}

	public static void deleteEvent(Scanner input) {
		if (listOfEvents.isEmpty()) {
			System.out.println("There are no events to remove");
			System.out.println();
			AgendaBuilderMenu.deleteEventMenu();
		} else {
			System.out.print("Enter Id of the event you want to remove: ");
			int id = getValidInteger(input);
			System.out.println();
			if (listOfEvents.containsKey(id)) {
				listOfEvents.remove(id);
				if (User.myAgenda.containsKey(id)) {
					User.myAgenda.remove(id);
				}
				System.out.printf("You sucessfully removed event with Id: %s%n", id);
				System.out.println();
				AgendaBuilderMenu.deleteEventMenu();
			} else {
				System.out.printf("Event with Id: \"%s\" does not exists%n", id);
				AgendaBuilderMenu.deleteEventMenu();
			}
		}
	}
}
