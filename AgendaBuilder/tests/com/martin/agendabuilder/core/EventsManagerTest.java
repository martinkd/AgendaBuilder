package com.martin.agendabuilder.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.martin.agendabuilder.dao.EventsDao;
import com.martin.agendabuilder.entity.Event;

public class EventsManagerTest {

	@Before
	public void clearAllEvents() {
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
	public void testAddEvent() {
		Event event = new Event();
		event.setId(1);
		EventsDao dao = new EventsDao();
		assertTrue("should add with no problem", dao.addEvent(event));
		assertEquals(1, dao.getEvent(1).getId());
		assertFalse("cannot add event with the same Id", dao.addEvent(event));
		assertNull("cannot get event which is not added", dao.getEvent(2));
	}

	@Test
	public void testUpdateEvent() {
		Event event = new Event();
		EventsDao dao =  new EventsDao();
		event.setId(1);
		assertFalse("cannot update event which is not in the list", dao.updateEvent(event));
		dao.addEvent(event);
		event.setName("newName");
		assertTrue("should update properly", dao.updateEvent(event));
	}

	@Test
	public void testRemoveEvent() {
		Event event = new Event();
		EventsDao dao =  new EventsDao();
		event.setId(1);
		assertFalse("cannot remove event which doesn't exist", dao.deleteEvent(1));
		dao.addEvent(event);
		assertTrue(dao.deleteEvent(1));
		assertFalse("cannot remove twice same event", dao.deleteEvent(1));
	}

	@Test
	public void testGetAllEvents() {
		EventsDao dao = new EventsDao();
		assertTrue(dao.getAllEvents().isEmpty());
		Event event1 = new Event();
		event1.setId(1);
		Event event2 = new Event();
		event2.setId(2);
		Event event3 = new Event();
		event3.setId(3);
		dao.addEvent(event1);
		dao.addEvent(event2);
		dao.addEvent(event3);
		assertEquals(3, dao.getAllEvents().size());
	}
}
