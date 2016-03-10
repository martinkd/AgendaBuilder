package com.martin.agendabuilder.core;

import java.util.ArrayList;
import java.util.List;

import com.martin.agendabuilder.dao.EventsDao;
import com.martin.agendabuilder.entity.Event;

public class UserEventsManager {

	public static boolean register(Integer id) {
		try {
			EventsDao dao = new EventsDao();
			return dao.searchById(id) != null && dao.register(id);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean unregister(Integer id) {
		try {
			EventsDao dao = new EventsDao();
			return dao.searchById(id) != null && dao.unRegister(id);
		} catch (Exception e) {
			return false;
		}
	}

	public static List<Event> getAgendaEvents() {
		List<Event> events = new ArrayList<Event>();
		EventsDao dao;
		try {
			dao = new EventsDao();
			for (Integer id : dao.getAllAgendaEvents()) {
				Event event = EventsManager.getEvent(id);
				if (event != null) {
					events.add(event);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return events;
	}
}
