package com.martin.agendabuilder.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.martin.agendabuilder.dao.AgendaEventsDao;
import com.martin.agendabuilder.dao.EventsDao;
import com.martin.agendabuilder.entity.Event;

public class AgendaEventsDaoTest {
	EventsDao eDao = new EventsDao();
	AgendaEventsDao aDao = new AgendaEventsDao();
	
	@Before
	public void clearAllEvents() throws SQLException {
		for (Event event : eDao.getAllEvents()) {
			eDao.deleteEvent(event.getId());
		}
	}

	@Test
	public void testRegister() throws SQLException {
		assertFalse("cannot register for event that doesn't exist", aDao.register(1));
		Event event = new Event();
		event.setId(1);
		eDao.addEvent(event);
		assertTrue(aDao.register(1));
		assertFalse("cannot register twice for the same event", aDao.register(1));
	}

	@Test
	public void testUnregister() throws SQLException {
		assertFalse("cannot unregister for event that doesn't exist or it's not in agenda",
				aDao.unRegister(1));
		Event event = new Event();
		event.setId(1);
		eDao.addEvent(event);
		aDao.register(1);
		assertTrue("should unregister", aDao.unRegister(1));
		assertFalse("cannot unregister twice for the same event", aDao.unRegister(1));
		eDao.deleteEvent(1);
		assertFalse("cannot unregister from event which is removed", aDao.unRegister(1));
	}

	@Test
	public void testGetAgendaEvents() throws SQLException {
		assertTrue(aDao.getAllAgendaIDs().isEmpty());
		Event event = new Event();
		event.setId(1);
		Event event2 = new Event();
		event2.setId(2);
		Event event3 = new Event();
		event3.setId(3);
		eDao.addEvent(event);
		eDao.addEvent(event2);
		eDao.addEvent(event3);
		aDao.register(1);
		aDao.register(2);
		assertEquals(2, aDao.getAllAgendaIDs().size());
		aDao.unRegister(1);
		assertEquals(1, aDao.getAllAgendaIDs().size());
		eDao.deleteEvent(2);
		assertTrue("Event which is removed should be removed from agenda too", aDao.getAllAgendaIDs().isEmpty());
	}
}
