package com.martin.agendabuilder.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputUtils {

	public static int getValidInteger(Scanner input) {
		while (true) {
			try {
				return Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {
				System.err.println("Enter valid number");
			}
		}
	}

	public static boolean getValidBoolean(Scanner input) {
		String txtBoolean = input.nextLine();
		return Boolean.parseBoolean(txtBoolean);
	}

	public static Date validDate(Scanner input) {
		while (true) {
			DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			dateFormat.setLenient(false);
			String txtDate = input.nextLine();
			try {
				Date date = dateFormat.parse(txtDate);
				return date;
			} catch (ParseException e) {
				System.err.println("Enter valid date format (dd.mm.yyyy)!");
			}
		}
	}
}
