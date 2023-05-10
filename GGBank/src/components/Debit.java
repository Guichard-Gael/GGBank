package components;

import java.time.LocalDate;

//1.3.3 Creation of the Debit class
public class Debit extends Flow {

	// Constructor
	
	public Debit(String comment, double amount, int targetAccountNumber, boolean effect, LocalDate dateOfFlow) {
		super(comment, amount, targetAccountNumber, effect, dateOfFlow);
	}
}
