package com.martin.agendabuilder.menu;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.martin.agendabuilder.core.EventsManager;
import com.martin.agendabuilder.entity.Event;
import com.martin.agendabuilder.util.InputUtils;

public class Operator {

	private static final int NAME = 1;
	private static final int COUNTRY = 2;
	private static final int LOCATION = 3;
	private static final int START_DATE = 4;
	private static final int END_DATE = 5;
	private static final int IS_FREE = 6;
	private static final int RETURN = 0;

	public static void createNewEvent(Scanner input) {
		Event newEvent = new Event();
		System.out.print("Id: ");

		int id = InputUtils.getValidInteger(input);
		if (EventsManager.getEvent(id) == null) {
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
		newEvent.setStartDate(InputUtils.validDate(input));
		System.out.print("Enter end date: ");
		newEvent.setEndDate(InputUtils.validDate(input));
		System.out.print("Enter \"true\" if the event is free: ");
		newEvent.setIsFreeEvent(InputUtils.getValidBoolean(input));

		EventsManager.addEvent(newEvent);

		System.out.printf("%nYou sucsesfully created event with id: \"%s\" %n", id);
		System.out.println();
		AgendaBuilderMenu.operatorMenu();
	}

	public static void showAllEvents(Scanner input) {
		List<Event> events = EventsManager.getAllEvents();
		if (events.isEmpty()) {
			System.out.println("There are no events to show");
			System.out.println();
			AgendaBuilderMenu.readEventMenu();
		} else {
			Collections.sort(events, new Comparator<Event>() {

				@Override
				public int compare(Event eventOne, Event eventTwo) {
					return eventOne.getName().compareTo(eventTwo.getName());
				}
			});
			for (Event event : events) {
				System.out.printf("Id: %s, Name: %s%n", event.getId(), event.getName());
				System.out.println();
			}
			while (true) {
				System.out.println("Press \"0\" (zero) to return");
				if (InputUtils.getValidInteger(input) == RETURN) {
					AgendaBuilderMenu.readEventMenu();
					break;
				}
			}
		}
	}

	public static void readEvent(Scanner input) {
		if (EventsManager.getAllEvents().isEmpty()) {
			System.out.println("There are no events to show");
			System.out.println();
			AgendaBuilderMenu.readEventMenu();
		} else {
			System.out.print("Enter Id of the event you want to read: ");
			int id = InputUtils.getValidInteger(input);
			System.out.println();
			Event event = EventsManager.getEvent(id);
			if (event != null) {
				System.out.println(event);
				while (true) {
					System.out.println("Press \"0\" (zero) to return");
					if (InputUtils.getValidInteger(input) == RETURN) {
						AgendaBuilderMenu.readEventMenu();
						break;
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
		if (EventsManager.getAllEvents().isEmpty()) {
			System.out.println("There are no events to edit");
			System.out.println();
			AgendaBuilderMenu.editEventMenu();
		} else {
			System.out.print("Enter Id of the event you want to edit: ");
			int id = InputUtils.getValidInteger(input);
			System.out.println();
			Event event = EventsManager.getEvent(id);
			if (event != null) {
				chooseDataToEdit(input, event);
			} else {
				System.out.printf("Event with Id \"%s\" does not exists%n", id);
				System.out.println();
				AgendaBuilderMenu.editEventMenu();
			}
		}
	}

	private static void chooseDataToEdit(Scanner input, Event currentEvent) {
		EventsManager.updateEvent(currentEvent);
		System.out.println(currentEvent);
		System.out.print("Choose data to edit (1-6) or 0 to return: ");
		switch (InputUtils.getValidInteger(input)) {
		case RETURN:
			AgendaBuilderMenu.editEventMenu();
			break;
		case NAME:
			System.out.println("Enter new name: ");
			String newName = input.nextLine();
			currentEvent.setName(newName);
			System.out.println("You sucessfully changed name of the event to: " + newName);
			chooseDataToEdit(input, currentEvent);
			break;
		case COUNTRY:
			System.out.println("Enter new country: ");
			String newCountry = input.nextLine();
			currentEvent.setCountry(newCountry);
			System.out.println("You sucessfully changed country of the event to: " + newCountry);
			chooseDataToEdit(input, currentEvent);
			break;
		case LOCATION:
			System.out.println("Enter new location: ");
			String newLocation = input.nextLine();
			currentEvent.setLocation(newLocation);
			System.out.println("You sucessfully changed location of the event to: " + newLocation);
			chooseDataToEdit(input, currentEvent);
			break;
		case START_DATE:
			System.out.println("Enter new start date: ");
			Date newStartDate = InputUtils.validDate(input);
			currentEvent.setStartDate(newStartDate);
			System.out.println("You sucessfully changed start date of the event to: " + newStartDate);
			chooseDataToEdit(input, currentEvent);
			break;
		case END_DATE:
			System.out.println("Enter new end date: ");
			Date newEndDate = InputUtils.validDate(input);
			currentEvent.setEndDate(newEndDate);
			System.out.println("You sucessfully changed start date of the event to: " + newEndDate);
			chooseDataToEdit(input, currentEvent);
			break;
		case IS_FREE:
			System.out.println("If you want to make the event free, enter \"true\": ");
			Boolean isFree = InputUtils.getValidBoolean(input);
			currentEvent.setIsFreeEvent(isFree);
			if (isFree) {
				System.out.println("You set the event free");
				chooseDataToEdit(input, currentEvent);
				break;
			} else {
				System.out.println("You set the event not free");
				chooseDataToEdit(input, currentEvent);
				break;
			}
		default:
			System.err.println("Enter valid number");
			chooseDataToEdit(input, currentEvent);
			break;
		}
	}

	public static void deleteEvent(Scanner input) {
		if (EventsManager.getAllEvents().isEmpty()) {
			System.out.println("There are no events to remove");
			System.out.println();
			AgendaBuilderMenu.deleteEventMenu();
		} else {
			System.out.print("Enter Id of the event you want to remove: ");
			int id = InputUtils.getValidInteger(input);
			System.out.println();
			if (EventsManager.getEvent(id) != null) {
				EventsManager.removeEvent(id);
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
