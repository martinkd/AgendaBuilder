package com.martin.agendabuilder.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.martin.agendabuilder.entity.Event;

public class UserEventsManager {

	private static Set<Integer> agenda = new TreeSet<Integer>();

	public static boolean register(Integer id) {
		return agenda.add(id);
	}

	public static boolean unregister(Integer id) {
		return EventsManager.getEvent(id) != null && agenda.remove(id);
	}

	public static List<Event> getAgendaEvents() {
		List<Event> events = new ArrayList<Event>();
		for (Integer id : agenda) {
			Event event = EventsManager.getEvent(id);
			if (event != null) {
				events.add(event);
			}
		}
		return events;
	}
}
