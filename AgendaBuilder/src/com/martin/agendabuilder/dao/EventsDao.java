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

	String dbUrl = "jdbc:mysql://localhost:3306/eventsDB";
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
			myStmt = myConn.prepareStatement("select * from events where id = ?");
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