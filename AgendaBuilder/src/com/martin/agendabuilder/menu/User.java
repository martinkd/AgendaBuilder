package com.martin.agendabuilder.menu;

import java.util.List;
import java.util.Scanner;

import com.martin.agendabuilder.core.EventsManager;
import com.martin.agendabuilder.core.UserEventsManager;
import com.martin.agendabuilder.entity.Event;
import com.martin.agendabuilder.util.InputUtils;

public class User {

	private static final int RETURN = 0;
	// public static Map<Integer, Event> myAgenda = new HashMap<Integer,
	// Event>();

	public static void showAllEvents(Scanner input) {

		List<Event> allEvents = EventsManager.getAllEvents();
		if (allEvents.isEmpty()) {
			System.out.println("There are no events to show");
			System.out.println();
			AgendaBuilderMenu.userMenu();
		} else {
			for (Event event : allEvents) {
				System.out.printf("Id: %s, Name: %s%n", event.getId(), event.getName());
				System.out.println();
			}
			while (true) {
				System.out.println("Press \"0\" (zero) to return");
				if (InputUtils.getValidInteger(input) == RETURN) {
					AgendaBuilderMenu.userMenu();
					break;
				}
			}
		}
	}

	public static void registerEvent(Scanner input) {
		if (EventsManager.getAllEvents().isEmpty()) {
			System.out.println("There are no events to register");
			System.out.println();
			AgendaBuilderMenu.userMenu();
		} else {
			System.out.print("Enter Id of event you want to register: ");
			int id = InputUtils.getValidInteger(input);
			System.out.println();
			if (EventsManager.getEvent(id) != null) {
				if (UserEventsManager.register(id)) {
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
		if (EventsManager.getAllEvents().isEmpty()) {
			System.out.println("There are no events to unregister");
			System.out.println();
			AgendaBuilderMenu.userMenu();
		} else {
			System.out.println("Enter Id of event you want to unregister: ");
			int id = InputUtils.getValidInteger(input);
			System.out.println();
			if (UserEventsManager.unregister(id)) {
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
		List<Event> agendaEvents = UserEventsManager.getAgendaEvents();
		if (agendaEvents.isEmpty()) {
			System.out.println("There are no events in your Agenda");
			System.out.println();
			AgendaBuilderMenu.userMenu();
		} else {
			for (Event event : agendaEvents) {
				System.out.printf("Id: %s, Name: %s%n", event.getId(), event.getName());
				System.out.println();
			}
			while (true) {
				System.out.println("Press \"0\" (zero) to return");
				if (InputUtils.getValidInteger(input) == RETURN) {
					AgendaBuilderMenu.userMenu();
					break;
				}
			}
		}
	}
}
