package com.martin.agendabuilder.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.martin.agendabuilder.dao.EventsDao;
import com.martin.agendabuilder.entity.Event;

public class EventsManager {

	private static Map<Integer, Event> events = new HashMap<Integer, Event>();

	public static List<Event> getAllEvents() {
		try {
			EventsDao dao = new EventsDao();
			return new ArrayList<Event>(dao.getAllEvents());
		} catch (Exception e) {
			return new ArrayList<Event>();
		}
	}

	public static Event getEvent(Integer id) {
		try {
			EventsDao dao = new EventsDao();
			return dao.searchById(id);
		} catch (Exception e) {
			return new Event();
		}
	}

	public static boolean addEvent(Event event) {
		boolean canAdd = !events.containsKey(event.getId());
		if (canAdd) {
			events.put(event.getId(), event);
			EventsDao dao = null;
			try {
				dao = new EventsDao();
			} catch (Exception e) {
				e.printStackTrace();
			}
			dao.insertIntoDb(event);
		}
		return canAdd;
	}

	public static boolean updateEvent(Event event) {
		try {
			EventsDao dao = new EventsDao();
			boolean canUpdate = dao.searchById(event.getId()) != null;
			if (canUpdate) {
				dao.updateEvent(event);
				return canUpdate;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean removeEvent(Integer id) {
		return events.remove(id) != null;
	}
}