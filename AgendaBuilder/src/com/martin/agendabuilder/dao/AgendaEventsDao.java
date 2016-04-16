package com.martin.agendabuilder.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AgendaEventsDao {
	private EventsDataAccess dataAccess = new EventsDataAccess();
	private EventsDao eventsDao = new EventsDao();
	private PreparedStatement myPrepStmt;
	private Statement myStmt;
	private ResultSet myRs;

	public boolean register(int id) throws SQLException {
		boolean canRegister = eventsDao.containsIdInTable(id, "events")
				&& !eventsDao.containsIdInTable(id, "agendaEvents");
		if (canRegister) {
			String sql = "INSERT INTO agendaevents (id) " + "SELECT id from events WHERE id = ?;";
			myPrepStmt = dataAccess.getMyConn().prepareStatement(sql);
			myPrepStmt.setInt(1, id);
			myPrepStmt.executeUpdate();
		}
		return canRegister;
	}

	public boolean unRegister(int id) throws SQLException {
		boolean canUnReg = eventsDao.containsIdInTable(id, "events")
				&& eventsDao.containsIdInTable(id, "agendaEvents");
		if (canUnReg) {
			String sql = "DELETE FROM agendaevents where id = ?;";
			myPrepStmt = dataAccess.getMyConn().prepareStatement(sql);
			myPrepStmt.setInt(1, id);
			myPrepStmt.executeUpdate();
		}
		return canUnReg;
	}

	public List<Integer> getAllAgendaIDs() throws SQLException {
		List<Integer> list = new ArrayList<Integer>();
		String sql = "SELECT id FROM agendaevents";
		myStmt = dataAccess.getMyConn().createStatement();
		myRs = myStmt.executeQuery(sql);
		while (myRs.next()) {
			int foundId = myRs.getInt("id");
			list.add(foundId);
		}
		return list;
	}

}