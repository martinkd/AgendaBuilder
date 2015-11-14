import java.util.InputMismatchException;
import java.util.Scanner;

public class AgendaBuilderMenu {

	public void mainMenu() {
		System.out.println("1. Use\n2. Exit\n");
		System.out.print("Enter option(1-2): ");
		Integer option = getSelectedOption();
		if (option != null) {
			switch (option) {
			case 1:
				useMenu();
				break;
			case 2:
				System.out.println("You exit the program");
				break;
			default:
				System.err.println("Enter valid number");
				mainMenu();
				break;
			}
		} else {
			mainMenu();
		}
	}

	private void useMenu() {
		System.out.println("1. User\n2. Operator\n3. Return\n");
		System.out.println("Enter option(1-3): ");
		Integer option = getSelectedOption();
		if (option != null) {
			switch (option) {
			case 1:
				userMenu();
				break;
			case 2:
				operatorMenu();
				break;
			case 3:
				mainMenu();
				break;
			default:
				System.err.println("Enter valid number!");
				useMenu();
				break;
			}
		} else {
			useMenu();
		}
	}

	private void userMenu() {
		System.out.println("1. List all\n2. Register for event\n3. Unregister from event\n4. My agenda\n5. Return\n");
		System.out.print("Enter option(1-5): ");
		Integer option = getSelectedOption();
		if (option != null) {
			switch (option) {
			case 1:
				// shows list of events
				break;
			case 2:
				System.out.print("Enter Id: ");
				break;
			case 3:
				System.out.print("Enter Id: ");
				break;
			case 4:
				// Shows registered events
				// Id, Name
			case 5:
				useMenu();
				break;
			default:
				System.err.println("Enter valid number");
				userMenu();
				break;
			}
		} else {
			userMenu();
		}
	}

	private void operatorMenu() {
		System.out.println("1. Read event\n2. Create new event\n3. Edit event\n4. Delete event\n5. Return\n");
		System.out.print("Enter option(1-5): ");
		Integer option = getSelectedOption();
		if (option != null) {
			switch (option) {
			case 1:
				readEventMenu();
				break;
			case 2:
				// prompt to enter data for event
				break;
			case 3:
				editEventMenu();
				break;
			case 4:
				deleteEventMenu();
				break;
			case 5:
				useMenu();
				break;
			default:
				System.err.println("Enter valid number");
				operatorMenu();
				break;
			}
		} else {
			operatorMenu();
		}
	}

	private void readEventMenu() {
		System.out.println("1. List all\n2. Find by Id\n3. Return\n");
		System.out.print("Enter option(1-3): ");
		Integer option = getSelectedOption();
		if (option != null) {
			switch (option) {
			case 1:
				Operator operator = new Operator();
				operator.showAllEvents();
				// list all events
				break;
			case 2:
				System.out.print("Enter Id: ");
				break;
			case 3:
				operatorMenu();
			default:
				System.err.println("Enter valid number");
				readEventMenu();
				break;
			}
		} else {
			readEventMenu();
		}
	}

	private void editEventMenu() {
		System.out.println("1. Find by Id\n2. Return\n");
		System.out.println("Enter option(1-2): ");
		Integer option = getSelectedOption();
		if (option != null) {
			switch (option) {
			case 1:
				System.out.println("Enter Id: ");

				break;
			case 2:
				operatorMenu();
				break;
			default:
				System.err.println("Enter valid number");
				editEventMenu();
				break;
			}
		} else {
			editEventMenu();
		}
	}

	private void deleteEventMenu() {
		System.out.println("1. Delete by Id\n2. Return\n");
		System.out.println("Enter opton(1-2)");
		Integer option = getSelectedOption();
		if (option != null) {
			switch (option) {
			case 1:
				System.out.println("Enter Id: ");
				break;
			case 2:
				operatorMenu();
				break;
			default:
				System.err.println("Enter valid number");
				editEventMenu();
				break;
			}
		} else {
			deleteEventMenu();
		}
	}

	private Integer getSelectedOption() {
		try {
			Scanner input = new Scanner(System.in);
			return input.nextInt();
		} catch (InputMismatchException ex) {
			System.err.println("Enter valid number!");
			return null;
		}
	}
}