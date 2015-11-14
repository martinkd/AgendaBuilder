import java.util.InputMismatchException;
import java.util.Scanner;

public class AgendaBuilderMenu {
	private static final int DELETE_EVENT_MENU_DELETE_BY_ID = 1;
	private static final int EDIT_EVENT_FIND_BY_ID = 1;
	private static final int READ_EVENT_MENU_FIND_BY_ID = 2;
	private static final int READ_EVENT_MENU_LIST_ALL = 1;
	private static final int OPERATOR_MENU_DELETE_EVENT = 4;
	private static final int OPERATOR_MENU_EDIT_EVENT = 3;
	private static final int OPERATOR_MENU_CREATE_NEW_EVENT = 2;
	private static final int OPERATOR_MENU_READ_EVENT = 1;
	private static final int USER_MENU_MY_AGENDA = 4;
	private static final int USER_MENU_UNREGISTER_FROM_EVENT = 3;
	private static final int USER_MENU_REGISTER_FOR_EVENT = 2;
	private static final int USER_MENU_LIST_ALL = 1;
	private static final int RETURN = 0;
	private static final int USE_MENU_OPERATOR = 2;
	private static final int USE_MENU_USER = 1;
	private static final int MAIN_MENU_EXIT = 2;
	private static final int MAIN_MENU_USE = 1;
	
	public void mainMenu() {
		System.out.println("1. Use\n2. Exit\n");
		System.out.print("Enter option(1-2): ");
		Integer option = getSelectedOption();
		if (option != null) {
			switch (option) {
			case MAIN_MENU_USE:
				useMenu();
				break;
			case MAIN_MENU_EXIT:
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
		System.out.println("1. User\n2. Operator\n0. Return\n");
		System.out.println("Enter option(1-3): ");
		Integer option = getSelectedOption();
		if (option != null) {
			switch (option) {
			case USE_MENU_USER:
				userMenu();
				break;
			case USE_MENU_OPERATOR:
				operatorMenu();
				break;
			case RETURN:
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
		System.out.println("1. List all\n2. Register for event\n3. Unregister from event\n4. My agenda\n0. Return\n");
		System.out.print("Enter option(1-5): ");
		Integer option = getSelectedOption();
		if (option != null) {
			switch (option) {
			case USER_MENU_LIST_ALL:
				// shows list of events
				break;
			case USER_MENU_REGISTER_FOR_EVENT:
				System.out.print("Enter Id: ");
				break;
			case USER_MENU_UNREGISTER_FROM_EVENT:
				System.out.print("Enter Id: ");
				break;
			case USER_MENU_MY_AGENDA:
				// Shows registered events
				// Id, Name
				break;
			case RETURN:
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
		System.out.println("1. Read event\n2. Create new event\n3. Edit event\n4. Delete event\n0. Return\n");
		System.out.print("Enter option(1-5): ");
		Integer option = getSelectedOption();
		if (option != null) {
			switch (option) {
			case OPERATOR_MENU_READ_EVENT:
				readEventMenu();
				break;
			case OPERATOR_MENU_CREATE_NEW_EVENT:
				// prompt to enter data for event
				break;
			case OPERATOR_MENU_EDIT_EVENT:
				editEventMenu();
				break;
			case OPERATOR_MENU_DELETE_EVENT:
				deleteEventMenu();
				break;
			case RETURN:
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
		System.out.println("1. List all\n2. Find by Id\n0. Return\n");
		System.out.print("Enter option(1-3): ");
		Integer option = getSelectedOption();
		if (option != null) {
			switch (option) {
			case READ_EVENT_MENU_LIST_ALL:
				Operator operator = new Operator();
				operator.showAllEvents();
				// list all events
				break;
			case READ_EVENT_MENU_FIND_BY_ID:
				System.out.print("Enter Id: ");
				break;
			case RETURN:
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
		System.out.println("1. Find by Id\n0. Return\n");
		System.out.println("Enter option(1-2): ");
		Integer option = getSelectedOption();
		if (option != null) {
			switch (option) {
			case EDIT_EVENT_FIND_BY_ID:
				System.out.println("Enter Id: ");

				break;
			case RETURN:
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
		System.out.println("1. Delete by Id\n0. Return\n");
		System.out.println("Enter opton(1-2)");
		Integer option = getSelectedOption();
		if (option != null) {
			switch (option) {
			case DELETE_EVENT_MENU_DELETE_BY_ID:
				System.out.println("Enter Id: ");
				break;
			case RETURN:
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