package com.martin.agendabuilder.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import com.martin.agendabuilder.entity.Event;

public class EventsManagerTest {

	@After
	public void clearAllEvents() {
		for (Event event : EventsManager.getAllEvents()) {
			EventsManager.removeEvent(event.getId());
		}
	}

	@Test
	public void testAddEvent() {
		Event event = new Event();
		event.setId(1);
		assertTrue(EventsManager.addEvent(event));
		assertEquals(1, EventsManager.getEvent(1).getId());
		assertFalse("cannot add event with the same Id", EventsManager.addEvent(event));
		assertNull("cannot get event which is not added", EventsManager.getEvent(2));
	}

	@Test
	public void testUpdateEvent() {
		Event event = new Event();
		event.setId(1);
		assertFalse("cannot update event that is not in the list", EventsManager.updateEvent(event));
		EventsManager.addEvent(event);
		assertTrue(EventsManager.updateEvent(event));
	}

	@Test
	public void testRemoveEvent() {
		Event event = new Event();
		event.setId(1);
		assertFalse("cannot remove event which doesn't exist", EventsManager.removeEvent(1));
		EventsManager.addEvent(event);
		assertTrue(EventsManager.removeEvent(1));
		assertFalse("cannot remove twice same event", EventsManager.removeEvent(1));
	}

	@Test
	public void testGetAllEvents() {
		assertEquals(0, EventsManager.getAllEvents().size());
		Event event1 = new Event();
		event1.setId(1);
		Event event2 = new Event();
		event2.setId(2);
		Event event3 = new Event();
		event3.setId(3);
		EventsManager.addEvent(event1);
		EventsManager.addEvent(event2);
		EventsManager.addEvent(event3);
		assertEquals(3, EventsManager.getAllEvents().size());
	}
}
