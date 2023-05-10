package components;

import java.time.LocalDate;

//1.3.3 Creation of the Flow class
public class Credit extends Flow{

	// Constructor
	
	public Credit(String comment, double amount, int targetAccountNumber, boolean effect, LocalDate dateOfFlow) {
		super(comment, amount, targetAccountNumber, effect, dateOfFlow);
	}
}
