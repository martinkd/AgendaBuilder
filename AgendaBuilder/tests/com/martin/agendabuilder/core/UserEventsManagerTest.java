package com.martin.agendabuilder.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.martin.agendabuilder.dao.EventsDao;
import com.martin.agendabuilder.entity.Event;

public class UserEventsManagerTest {

	@Before
	public void clearAllEvents() throws SQLException {
		EventsDao dao = new EventsDao();
		for (Event event : dao.getAllEvents()) {
			dao.deleteEvent(event.getId());
		}
	}
	
	@AfterClass
	public static void cleanTestVariables () {
		EventsDao dao = new EventsDao();
		for (Event event : dao.getAllEvents()) {
			dao.deleteEvent(event.getId());
		}
	}

	@Test
	public void testRegister() {
		EventsDao dao = new EventsDao();
		assertFalse("cannot register for event that doesn't exist", dao.register(1));
		Event event = new Event();
		event.setId(1);
		dao.addEvent(event);
		assertTrue(UserEventsManager.register(1));
		assertFalse("cannot register twice for the same event", dao.register(1));
	}

	@Test
	public void testUnregister() {
		EventsDao dao = new EventsDao();
		assertFalse("cannot unregister for event that doesn't exist or it's not in agenda",
				dao.unRegister(1));
		Event event = new Event();
		event.setId(1);
		dao.addEvent(event);
		dao.register(1);
		assertTrue(dao.unRegister(1));
		assertFalse("cannot unregister twice for the same event", dao.unRegister(1));
		dao.deleteEvent(1);
		assertFalse("cannot unregister from event which is removed", dao.unRegister(1));
	}

	@Test
	public void testGetAgendaEvents() throws SQLException {
		EventsDao dao = new EventsDao();
		assertTrue(dao.getAllAgendaIDs().isEmpty());
		Event event = new Event();
		event.setId(1);
		Event event2 = new Event();
		event2.setId(2);
		Event event3 = new Event();
		event3.setId(3);
		dao.addEvent(event);
		dao.addEvent(event2);
		dao.addEvent(event3);
		dao.register(1);
		dao.register(2);
		assertEquals(2, dao.getAllAgendaIDs().size());
		dao.unRegister(1);
		assertEquals(1, dao.getAllAgendaIDs().size());
		dao.deleteEvent(2);
		assertTrue("Event which is removed should be removed from agenda too", dao.getAllAgendaIDs().isEmpty());
	}
}
