import java.util.Scanner;

public class AgendaBuilderMenu {
	private static int option;

	public void mainMenu() {
		Scanner input = new Scanner(System.in);
		System.out.println("1. Use\n2. Exit\n");
		System.out.println("Enter option(1-2): ");
		try {
			option = input.nextInt();
			if (option == 1) {
				useMenu();
			} else if (option == 2) {
				System.out.println("You exit the program");
			} else {
				System.err.println("Enter valid number!");
				mainMenu();
			}
		} catch (Exception e) {
			System.err.println("Enter valid NUMBER!");
			mainMenu();
		} finally {
			input.close();
		}
	}

	private void useMenu() {
		Scanner input = new Scanner(System.in);
		System.out.println("1. User\n2. Operator\n3. Return\n");
		System.out.println("Enter option(1-3): ");
		try {
			option = input.nextInt();
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
		} catch (Exception e) {
			System.err.println("Enter valid number!");
			useMenu();
		} finally {
			input.close();
		}
	}

	private void userMenu() {
		Scanner input = new Scanner(System.in);
		System.out.println("1. List all\n2. Register for event\n3. Unregister from event\n4. My agenda\n5. Return\n");
		System.out.println("Enter option(1-5): ");
		try {
			option = input.nextInt();
			switch (option) {
			case 1:
				// shows list of events
				break;
			case 2:
				System.out.println("Enter Id: ");
				break;
			case 3:
				System.out.println("Enter Id: ");
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
		} catch (Exception e) {
			System.err.println("Enter valid number");
			userMenu();
		} finally {
			input.close();
		}
	}

	private void operatorMenu() {
		Scanner input = new Scanner(System.in);
		System.out.println("1. Read event\n2. Create new event\n3. Edit event\n4. Delete event\n5. Return\n");
		System.out.println("Enter option(1-5): ");
		try {
			option = input.nextInt();
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
		} catch (Exception e) {
			System.err.println("Enter valid number");
			operatorMenu();
		} finally {
			input.close();
		}
	}

	private void readEventMenu() {
		Scanner input = new Scanner(System.in);
		System.out.println("1. List all\n2. Find by Id\n3. Return\n");
		System.out.println("Enter option(1-3)");
		try {
			option = input.nextInt();
			switch (option) {
			case 1:
				// list all events
				break;
			case 2:
				System.out.println("Enter Id: ");
				break;
			case 3:
				operatorMenu();
			default:
				System.err.println("Enter valid number");
				readEventMenu();
				break;
			}
		} catch (Exception e) {
			System.err.println("Enter valid number");
			readEventMenu();
		} finally {
			input.close();
		}
	}

	private void editEventMenu() {
		Scanner input = new Scanner(System.in);
		System.out.println("1. Find by Id\n2. Return\n");
		System.out.println("Enter option(1-2): ");
		try {
			option = input.nextInt();
			if (option == 1) {
				System.out.println("Enter Id: ");
			} else if (option == 2) {
				operatorMenu();
			} else {
				System.err.println("Enter valid number");
				editEventMenu();
			}
		} catch (Exception e) {
			System.err.println("Enter valid number");
			editEventMenu();
		} finally {
			input.close();
		}
	}

	private void deleteEventMenu() {
		Scanner input = new Scanner(System.in);
		System.out.println("1. Delete by Id\n2. Return\n");
		System.out.println("Enter opton(1-2)");
		try {
			option = input.nextInt();
			if (option == 1) {
				System.out.println("Enter Id: ");
			} else if (option == 2) {
				operatorMenu();
			} else {
				System.err.println("Enter valid number");
				deleteEventMenu();
			}
		} catch (Exception e) {
			System.err.println("Enter valid number");
			deleteEventMenu();
		} finally {
			input.close();
		}
	}
}