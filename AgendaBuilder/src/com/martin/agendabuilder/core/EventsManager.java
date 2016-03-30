package com.martin.agendabuilder.core;

import java.util.ArrayList;
import java.util.List;

import com.martin.agendabuilder.dao.EventsDao;
import com.martin.agendabuilder.entity.Event;

public class EventsManager {

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
			return dao.getEvent(id);
		} catch (Exception e) {
			return new Event();
		}
	}

	public static boolean addEvent(Event event) {
		try {
			EventsDao dao = new EventsDao();
			if (!getAllEvents().contains(event.getId())) {
				return dao.addEvent(event);
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static boolean updateEvent(Event event) {
		try {
			EventsDao dao = new EventsDao();
			if (getAllEvents().contains(event.getId())){				
				return dao.updateEvent(event);
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static boolean removeEvent(Integer id) {
		try {
			EventsDao dao = new EventsDao();
			if (getAllEvents().contains(id)){
				return dao.deleteEvent(id);				
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}