package com.martin.agendabuilder.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.martin.agendabuilder.entity.Event;

public class EventsManager {

	private static Map<Integer, Event> events = new HashMap<Integer, Event>();

	public static List<Event> getAllEvents() {
		return new ArrayList<Event>(events.values());
	}

	public static Event getEvent(Integer id) {
		return events.get(id);
	}
	
	public static boolean addEvent(Event event) {
		boolean canAdd = !events.containsKey(event.getId());
		if (canAdd) {
			events.put(event.getId(), event);
		}
		return canAdd;
	}

	public static boolean updateEvent(Event event) {
		boolean canUpdate = events.containsKey(event.getId());
		if (canUpdate) {
			events.put(event.getId(), event);
		}
		return canUpdate;
	}
	
	public static boolean removeEvent(Integer id) {
		return events.remove(id) != null;
	}
}