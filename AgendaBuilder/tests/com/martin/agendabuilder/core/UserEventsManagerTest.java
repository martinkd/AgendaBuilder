package com.martin.agendabuilder.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import com.martin.agendabuilder.entity.Event;

public class UserEventsManagerTest {

	@After
	public void clearAllEvents() {
		for (Event event : EventsManager.getAllEvents()) {
			EventsManager.removeEvent(event.getId());
		}
	}

	@Test
	public void testRegister() {
		assertFalse("cannot register for event that doesn't exist", UserEventsManager.register(1));
		Event event = new Event();
		event.setId(1);
		EventsManager.addEvent(event);
		assertTrue(UserEventsManager.register(1));
		assertFalse("cannot register twice for the same event", UserEventsManager.register(1));
	}

	@Test
	public void testUnregister() {
		assertFalse("cannot unregister for event that doesn't exist or it's not in agenda",
				UserEventsManager.unregister(1));
		Event event = new Event();
		event.setId(1);
		EventsManager.addEvent(event);
		UserEventsManager.register(1);
		assertTrue(UserEventsManager.unregister(1));
		assertFalse("cannot unregister twice for the same event", UserEventsManager.unregister(1));
		EventsManager.removeEvent(1);
		assertFalse("cannot unregister from event which is removed", UserEventsManager.unregister(1));
	}

	@Test
	public void testGetAgendaEvents() {
		assertTrue(UserEventsManager.getAgendaEvents().isEmpty());
		Event event = new Event();
		event.setId(1);
		Event event2 = new Event();
		event2.setId(2);
		Event event3 = new Event();
		event3.setId(3);
		EventsManager.addEvent(event);
		EventsManager.addEvent(event2);
		EventsManager.addEvent(event3);
		UserEventsManager.register(1);
		UserEventsManager.register(2);
		assertEquals(2, UserEventsManager.getAgendaEvents().size());
		UserEventsManager.unregister(1);
		assertEquals(1, UserEventsManager.getAgendaEvents().size());
		EventsManager.removeEvent(2);
		assertTrue("Event which is removed should be removed from agenda too", UserEventsManager.getAgendaEvents().isEmpty());
	}
}
