package com.martin.agendabuilder.util;

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
			String inputDate = input.nextLine();
			if (inputDate != null && !inputDate.isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
				try {
					sdf.setLenient(false);
					return sdf.parse(inputDate);
				} catch (ParseException e) {
					System.err.println("Enter valid date");
				}
			} else break;
		}
		return null;
	}
}
