package com.martin.agendabuilder.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.martin.agendabuilder.entity.Event;

public class EventsDao {

	private Connection myConn;

	public EventsDao() throws Exception	{

	String dbUrl = "jdbc:mysql://localhost:3306/eventsDB?useSSL=false";
	String user = "student";
	String pass = "student";
	myConn = DriverManager.getConnection(dbUrl, user, pass);

	}

	public List<Event> getAllEvents() throws SQLException {
		List<Event> list = new ArrayList<>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from events");

			while (myRs.next()) {
				Event tempEmployee = convertRowToEvent(myRs);
				list.add(tempEmployee);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public Event searchById (int id) throws Exception{
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Event tempEvent = null;
		try {
			myStmt = myConn.prepareStatement("SELECT * FROM events WHERE id = ?");
			myStmt.setInt(1, id);

			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				tempEvent = convertRowToEvent(myRs);
			}
			return tempEvent;
			
		} finally {
			close(myStmt, myRs);
		}
	}
	
	public void insertIntoDb(Event event) {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("INSERT INTO events(id, name, country, location, startdate, enddate, isfree)"
					+ "values (?, ?, ?, ?, ?, ?, ?)");
			myStmt.setInt(1, event.getId());
			myStmt.setString(2, event.getName());
			myStmt.setString(3, event.getCountry());
			myStmt.setString(4, event.getLocation());
			myStmt.setDate(5, event.getStartDate() == null? null : new java.sql.Date(event.getStartDate().getTime()));
			myStmt.setDate(6, event.getEndDate() == null? null : new java.sql.Date(event.getEndDate().getTime()));
			myStmt.setBoolean(7, event.getIsFreeEvent());
			myStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void updateEvent (Event event) {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("UPDATE events"
					+ " SET name = ?, country = ?, location = ?,"
					+ " startDate = ?, endDate = ?, isFree =? where id = ?" );
			myStmt.setString(1, event.getName());
			myStmt.setString(2, event.getCountry());
			myStmt.setString(3, event.getLocation());
			myStmt.setDate(4, event.getStartDate() == null? null : new java.sql.Date(event.getStartDate().getTime()));
			myStmt.setDate(5, event.getEndDate() == null? null : new java.sql.Date(event.getEndDate().getTime()));
			myStmt.setBoolean(6, event.getIsFreeEvent());
			myStmt.setInt(7, event.getId());
			myStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteEvent (int id) {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("DELETE  FROM events WHERE id = ?");
			myStmt.setInt(1, id);
			myStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Event convertRowToEvent(ResultSet myRs) throws SQLException {
		int id = myRs.getInt("id");
		String name = myRs.getString("name");
		String country = myRs.getString("country");
		String location = myRs.getString("location");
		Date startDate = myRs.getDate("startDate");
		Date endDate = myRs.getDate("endDate");
		boolean isFreeEvent = myRs.getBoolean("isFree");

		Event theEvent = new Event(id, name, country, location, startDate, endDate, isFreeEvent);
		return theEvent;
	}

	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {

		}

		if (myConn != null) {
			myConn.close();
		}
	}

	private static void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);
	}
	
}