package com.martin.agendabuilder.menu;
import java.sql.SQLException;
import java.util.Scanner;

import com.martin.agendabuilder.util.InputUtils;

public class AgendaBuilderMenu {
	private static final int DELETE_EVENT_MENU_DELETE_BY_ID = 1;
	private static final int EDIT_EVENT_MENU_FIND_BY_ID = 1;
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
	private static final int MAIN_MENU_USE = 1;
	private static Scanner input = new Scanner(System.in);

	public static void mainMenu() throws SQLException {
		System.out.println("\tMAIN");
		System.out.println("1. Use\n0. Exit\n");
		System.out.print("Enter option: ");
		Integer option = InputUtils.getValidInteger(input);
		System.out.println();
		switch (option) {
		case MAIN_MENU_USE:
			useMenu();
			break;
		case RETURN:
			System.out.println("You exit the program");
			break;
		default:
			System.err.println("Enter valid number");
			mainMenu();
			break;
		}
	}

	private static void useMenu() throws SQLException {
		System.out.println("\tUSE");
		System.out.println("1. User\n2. Operator\n0. Return\n");
		System.out.print("Enter option: ");
		Integer option = InputUtils.getValidInteger(input);
		System.out.println();
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
	}

	public static void userMenu() throws SQLException {
		System.out.println("\tUSER");
		System.out.println("1. List all\n2. Register for event\n3. Unregister from event\n4. My agenda\n0. Return\n");
		System.out.print("Enter option: ");
		Integer option = InputUtils.getValidInteger(input);
		System.out.println();
		switch (option) {
		case USER_MENU_LIST_ALL:
			User.showAllEvents(input);
			break;
		case USER_MENU_REGISTER_FOR_EVENT:
			User.registerEvent(input);
			break;
		case USER_MENU_UNREGISTER_FROM_EVENT:
			User.unRegisterEvent(input);
			break;
		case USER_MENU_MY_AGENDA:
			User.showMyAgenda(input);
			break;
		case RETURN:
			useMenu();
			break;
		default:
			System.err.println("Enter valid number");
			userMenu();
			break;
		}
	}

	public static void operatorMenu() throws SQLException {
		System.out.println("\tOPERATOR");
		System.out.println("1. Read event\n2. Create new event\n3. Edit event\n4. Delete event\n0. Return\n");
		System.out.print("Enter option: ");
		Integer option = InputUtils.getValidInteger(input);
		System.out.println();
		switch (option) {
		case OPERATOR_MENU_READ_EVENT:
			readEventMenu();
			break;
		case OPERATOR_MENU_CREATE_NEW_EVENT:
			Operator.createNewEvent(input);
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
	}

	public static void readEventMenu() throws SQLException {
		System.out.println("\tREAD");
		System.out.println("1. List all\n2. Find by Id\n0. Return\n");
		System.out.print("Enter option: ");
		Integer option = InputUtils.getValidInteger(input);
		System.out.println();
		switch (option) {
		case READ_EVENT_MENU_LIST_ALL:
			Operator.showAllEvents(input);
			break;
		case READ_EVENT_MENU_FIND_BY_ID:
			Operator.readEvent(input);
			break;
		case RETURN:
			operatorMenu();
			break;
		default:
			System.err.println("Enter valid number");
			readEventMenu();
			break;
		}
	}

	public static void editEventMenu() throws SQLException {
		System.out.println("\tEDIT");
		System.out.println("1. Find by Id\n0. Return\n");
		System.out.print("Enter option: ");
		Integer option = InputUtils.getValidInteger(input);
		System.out.println();
		switch (option) {
		case EDIT_EVENT_MENU_FIND_BY_ID:
			Operator.editEvent(input);
			break;
		case RETURN:
			operatorMenu();
			break;
		default:
			System.err.println("Enter valid number");
			editEventMenu();
			break;
		}
	}

	public static void deleteEventMenu() throws SQLException {
		System.out.println("\tDELETE");
		System.out.println("1. Delete by Id\n0. Return\n");
		System.out.print("Enter opton: ");
		Integer option = InputUtils.getValidInteger(input);
		System.out.println();
		switch (option) {
		case DELETE_EVENT_MENU_DELETE_BY_ID:
			Operator.deleteEvent(input);
			break;
		case RETURN:
			operatorMenu();
			break;
		default:
			System.err.println("Enter valid number");
			deleteEventMenu();
			break;
		}
	}
}