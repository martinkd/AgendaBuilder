package com.martin.agendabuilder.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.martin.agendabuilder.dao.EventsDao;
import com.martin.agendabuilder.entity.Event;
import com.martin.agendabuilder.util.InputUtils;

public class User {

	private static final int RETURN = 0;

	public static void showAllEvents(Scanner input) {
		EventsDao dao = new EventsDao();
		if (dao.isEmpty()) {
			System.out.println("There are no events to show");
			System.out.println();
			AgendaBuilderMenu.userMenu();
		} else {
			List<Event> events = dao.getAllEvents();
			Operator.getSortedEvents(events);
			returnUserMenu(input);
		}
	}

	private static void returnUserMenu(Scanner input) {
		while (true) {
			System.out.println("Press \"0\" (zero) to return");
			if (InputUtils.getValidInteger(input) == RETURN) {
				AgendaBuilderMenu.userMenu();
				break;
			}
		}
	}

	public static void registerEvent(Scanner input) {
		EventsDao dao = new EventsDao();
		if (dao.isEmpty()) {
			System.out.println("There are no events to register");
			System.out.println();
			AgendaBuilderMenu.userMenu();
		} else {
			System.out.print("Enter Id of event you want to register: ");
			int id = InputUtils.getValidInteger(input);
			System.out.println();
			if (dao.contains(id)) {
				if (dao.register(id)) {
					System.out.printf("You sucessfully registered for event with Id: \"%s\"%n", id);
					System.out.println();
					AgendaBuilderMenu.userMenu();
				} else {
					System.out.printf("Event with Id: \"%s\" you are trying to register is already in your Agenda%n",
							id);
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

	public static void unRegisterEvent(Scanner input) {
		EventsDao dao = new EventsDao();
		if (dao.isEmpty() || dao.getAllAgendaIDs().isEmpty()) {
			System.out.println("There are no events to unregister");
			System.out.println();
			AgendaBuilderMenu.userMenu();
		} else {
			System.out.println("Enter Id of event you want to unregister: ");
			int id = InputUtils.getValidInteger(input);
			System.out.println();
			if (dao.unRegister(id)) {
				System.out.printf("You sucessfully unregistered from event with Id: \"%s\"%n", id);
				System.out.println();
				AgendaBuilderMenu.userMenu();
			} else {
				System.out.printf("Unregistration NOT complete. Event with Id: \"%s\" does not exists in your Agenda%n",
						id);
				System.out.println();
				AgendaBuilderMenu.userMenu();
			}
		}
	}

	public static void showMyAgenda(Scanner input) {
		EventsDao dao = new EventsDao();
		List<Integer> agendaIds = dao.getAllAgendaIDs();
		if (agendaIds.isEmpty()) {
			System.out.println("There are no events in your Agenda");
			System.out.println();
			AgendaBuilderMenu.userMenu();
		} else {
			List<Event> events = new ArrayList<Event>();
			for (int id : agendaIds) {
				events.add(dao.getEvent(id));
			}
			Operator.getSortedEvents(events);
			returnUserMenu(input);
		}
	}
}
