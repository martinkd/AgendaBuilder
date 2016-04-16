package com.martin.agendabuilder.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.martin.agendabuilder.dao.EventsDao;
import com.martin.agendabuilder.entity.Event;

public class EventsDaoTest {
	EventsDao eDao = new EventsDao();
	
	@Before
	public void clearAllEvents() throws SQLException {
		for (Event event : eDao.getAllEvents()) {
			eDao.deleteEvent(event.getId());
		}
	}

	@Test
	public void testAddEvent() throws SQLException {
		Event event = new Event();
		event.setId(1);
		assertTrue("should add with no problem", eDao.addEvent(event));
		assertEquals(1, eDao.getEvent(1).getId());
		assertFalse("cannot add event with the same Id", eDao.addEvent(event));
		assertNull("cannot get event which is not added", eDao.getEvent(2));
	}

	@Test
	public void testUpdateEvent() throws SQLException {
		Event event = new Event();
		event.setId(1);
		assertFalse("cannot update event which is not in the list", eDao.updateEvent(event));
		eDao.addEvent(event);
		event.setName("newName");
		assertTrue("should update properly", eDao.updateEvent(event));
	}

	@Test
	public void testRemoveEvent() throws SQLException {
		Event event = new Event();
		event.setId(1);
		assertFalse("cannot remove event which doesn't exist", eDao.deleteEvent(1));
		eDao.addEvent(event);
		assertTrue(eDao.deleteEvent(1));
		assertFalse("cannot remove twice same event", eDao.deleteEvent(1));
	}

	@Test
	public void testGetAllEvents() throws SQLException {
		assertTrue(eDao.getAllEvents().isEmpty());
		Event event1 = new Event();
		event1.setId(1);
		Event event2 = new Event();
		event2.setId(2);
		Event event3 = new Event();
		event3.setId(3);
		eDao.addEvent(event1);
		eDao.addEvent(event2);
		eDao.addEvent(event3);
		assertEquals(3, eDao.getAllEvents().size());
	}
}
