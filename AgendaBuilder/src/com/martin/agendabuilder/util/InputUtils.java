package com.martin.agendabuilder.util;
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
}
