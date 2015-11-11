import java.text.ParseException;

public class testClass {

	public static void main(String[] args) throws ParseException {

	AgendaBuilderOperator operator = new AgendaBuilderOperator();
	String txtDateStart = "15/02/2015";
	String txtDateEnd = "18/02/2015";
	operator.createEvent(2, "martin", "BG", "BS", txtDateStart, txtDateEnd, true);
	operator.createEvent(5, "gosho", "Vn", "hanoi", txtDateStart, txtDateEnd, true);
	operator.createEvent(4, "sunny", "bg", "Pld", txtDateStart, txtDateEnd, true);
	operator.showAllEvents();
	operator.readEvent(4);
	}
}
	