package AgendaBuilderApp;

import java.sql.SQLException;

import com.martin.agendabuilder.menu.AgendaBuilderMenu;

public class RunAgendaBuilder {
	public static void main(String[] args) {
		try {
			AgendaBuilderMenu.mainMenu();
		} catch (SQLException e) {
			System.err.println("SQL connection failed");
			e.printStackTrace();
		}
	}
}
