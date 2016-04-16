package com.martin.agendabuilder.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EventsDataAccess {
	private Connection myConn;
	private Statement myStmt;

	public Connection getMyConn() {
		return myConn;
	}

	public void setMyConn(Connection myConn) {
		this.myConn = myConn;
	}

	public EventsDataAccess() {
		try {
			connectDb();
			createTables();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void connectDb() throws SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/eventsDB?useSSL=false";
		String user = "student";
		String pass = "student";
		myConn = DriverManager.getConnection(dbUrl, user, pass);
	}

	private void createTables() throws SQLException {
		createEventsTable();
		createAgendaEventsTable();
	}

	private void createEventsTable() throws SQLException {
		myStmt = myConn.createStatement();
		String createTableEvents = "CREATE TABLE IF NOT EXISTS events (" + "id int PRIMARY KEY," + "name varchar(45),"
				+ "country varchar(45)," + "location varchar(45)," + "startdate date," + "enddate date,"
				+ "isfree bit default 0," + "UNIQUE INDEX id_unique (id));";
		myStmt.executeUpdate(createTableEvents);
	}

	private void createAgendaEventsTable() throws SQLException {
		String createTableAgendaEvents = "CREATE TABLE IF NOT EXISTS agendaevents(" + "id INT NULL, "
				+ "UNIQUE INDEX unique_id (id), " + "FOREIGN KEY (id) REFERENCES events (id) "
				+ "ON DELETE CASCADE ON UPDATE CASCADE);";
		myStmt.executeUpdate(createTableAgendaEvents);
	}
}
