package com.martin.agendabuilder.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.martin.agendabuilder.entity.Event;

public class EventsDao {

	public Statement myStmt;
	public ResultSet myRs;
	public PreparedStatement myPrepStmt;
	public EventsDataAccess dataAccess = new EventsDataAccess();

	public boolean containsIdInTable(int id, String table) throws SQLException {
		boolean containsId = false;
		String sql = "SELECT id FROM " + table + " WHERE id = ?;";
		myPrepStmt = dataAccess.getMyConn().prepareStatement(sql);
		myPrepStmt.setInt(1, id);
		myRs = myPrepStmt.executeQuery();
		containsId = myRs.next();
		return containsId;
	}

	public List<Event> getAllEvents() throws SQLException {
		List<Event> list = new ArrayList<Event>();
		String sql = "SELECT * FROM events;";
		myStmt = dataAccess.getMyConn().createStatement();
		myRs = myStmt.executeQuery(sql);
		while (myRs.next()) {
			Event tempEvent = convertRowToEvent(myRs);
			list.add(tempEvent);
		}
		return list;
	}

	public boolean isEmpty() throws SQLException {
		return getAllEvents().isEmpty();
	}

	public Event getEvent(int id) throws SQLException {
		Event foundEvent = null;
		String sql = "SELECT * FROM events WHERE id = ?;";
		if (containsIdInTable(id, "events")) {
			myPrepStmt = dataAccess.getMyConn().prepareStatement(sql);
			myPrepStmt.setInt(1, id);
			myRs = myPrepStmt.executeQuery();
			if (myRs.next()) {
				foundEvent = convertRowToEvent(myRs);
			}
		}
		return foundEvent;
	}

	public boolean addEvent(Event event) throws SQLException {
		boolean canAdd = !containsIdInTable(event.getId(), "events");
		if (canAdd) {
			String sql = "INSERT INTO events" + "(id, name, country, location, startdate, enddate, isfree)"
					+ "values (?, ?, ?, ?, ?, ?, ?);";
			convertEventToRow(event, sql);
		}
		return canAdd;
	}

	private void convertEventToRow(Event event, String sql) throws SQLException {
		myPrepStmt = dataAccess.getMyConn().prepareStatement(sql);
		myPrepStmt.setInt(1, event.getId());
		myPrepStmt.setString(2, event.getName());
		myPrepStmt.setString(3, event.getCountry());
		myPrepStmt.setString(4, event.getLocation());
		myPrepStmt.setDate(5, startDate(event));
		myPrepStmt.setDate(6, endDate(event));
		myPrepStmt.setBoolean(7, event.getIsFreeEvent());
		myPrepStmt.executeUpdate();
	}

	private Date endDate(Event event) {
		return event.getEndDate() == null ? null : new java.sql.Date(event.getEndDate().getTime());
	}

	private Date startDate(Event event) {
		return event.getStartDate() == null ? null : new java.sql.Date(event.getStartDate().getTime());
	}

	public boolean updateEvent(Event event) throws SQLException {
		boolean completeUpdate = false;
		if (containsIdInTable(event.getId(), "events")) {
			String sql = "UPDATE events" + " SET name = ?, country = ?, location = ?,"
					+ " startDate = ?, endDate = ?, isFree =? where id = ?";
			convertEventToRow(event, sql);
			completeUpdate = true;
		}
		return completeUpdate;
	}

	public boolean deleteEvent(int id) throws SQLException {
		boolean completeDelete = false;
		if (containsIdInTable(id, "events")) {
			String sql = "DELETE  FROM events WHERE id = ?;";
			myPrepStmt = dataAccess.getMyConn().prepareStatement(sql);
			myPrepStmt.setInt(1, id);
			myPrepStmt.executeUpdate();
			completeDelete = true;
		}
		return completeDelete;
	}

	private Event convertRowToEvent(ResultSet myRs) throws SQLException {
		int id = myRs.getInt("id");
		String name = myRs.getString("name");
		String country = myRs.getString("country");
		String location = myRs.getString("location");
		Date startDate = myRs.getDate("startDate");
		Date endDate = myRs.getDate("endDate");
		boolean isFreeEvent = myRs.getBoolean("isFree");
		Event event = new Event(id, name, country, location, startDate, endDate, isFreeEvent);
		return event;
	}

}