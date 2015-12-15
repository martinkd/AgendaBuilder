import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class User {
	private static final int RETURN = 0;
	public static Map<Integer, Event> myAgenda = new HashMap<Integer, Event>();
	
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
	
	public static void showAllEvents(Scanner input) {
		if (Operator.listOfEvents.isEmpty()) {
			System.out.println("There are no events to show");
			System.out.println();
			AgendaBuilderMenu.userMenu();
		} else {
			for (Entry<Integer, Event> event : Operator.listOfEvents.entrySet()) {
				System.out.printf("Id: %s, Name: %s%n", event.getValue().getId(), event.getValue().getName());
				System.out.println();
			}
			while (true) {
				System.out.println("Press \"0\" (zero) to return");
				if (getValidInteger(input) == RETURN) {
					AgendaBuilderMenu.userMenu();
					break;
				}
			}
		}
	}
	
	public static void registerEvent (Scanner input) {
		if (Operator.listOfEvents.isEmpty()) {
			System.out.println("There are no events to register");
			System.out.println();
			AgendaBuilderMenu.userMenu();
		} else {
			System.out.print("Enter Id of event you want to register: ");
			int id = getValidInteger(input);
			System.out.println();
			if (Operator.listOfEvents.containsKey(id)) {
				if (!myAgenda.containsKey(id)) {
					myAgenda.put(id, Operator.listOfEvents.get(id));
					System.out.printf("You sucessfully registered for event with Id: \"%s\"%n", id);
					System.out.println();
					AgendaBuilderMenu.userMenu();
				} else {
					System.out.printf("Event with Id: \"%s\" you are trying to register is already in your Agenda%n", id);
					System.out.println();
					AgendaBuilderMenu.userMenu();
				}
			} else {
				System.out.printf("Registration NOT complete. Event with Id: \"%s\" does not exists%n", id);
				System.out.println();
				AgendaBuilderMenu.userMenu();
			}
		}
	}
	
	public static void unrRegisterEvent (Scanner input) {
		if (Operator.listOfEvents.isEmpty()) {
			System.out.println("There are no events to unregister");
			System.out.println();
			AgendaBuilderMenu.userMenu();
		} else {
			System.out.println("Enter Id of event you want to unregister: ");
			int id = getValidInteger(input);
			System.out.println();
			if (myAgenda.containsKey(id)) {
				myAgenda.remove(id);
				System.out.printf("You sucessfully unregistered from event with Id: \"%s\"%n", id);
				System.out.println();
				AgendaBuilderMenu.userMenu();
			} else {
				System.out.printf("Unregistration NOT complete. Event with Id: \"%s\" does not exists in your Agenda%n", id);
				System.out.println();
				AgendaBuilderMenu.userMenu();
			}
		}
	}
	
	public static void showMyAgenda (Scanner input){
		if (myAgenda.isEmpty()) {
			System.out.println("There are no events in your Agenda");
			System.out.println();
			AgendaBuilderMenu.userMenu();
		} else {
			for (Entry<Integer, Event> event : myAgenda.entrySet()) {
				System.out.printf("Id: %s, Name: %s%n", event.getValue().getId(), event.getValue().getName());
				System.out.println();
			}
			while (true) {
				System.out.println("Press \"0\" (zero) to return");
				if (getValidInteger(input) == RETURN) {
					AgendaBuilderMenu.userMenu();
					break;
				}
			}
		}
	}
}
