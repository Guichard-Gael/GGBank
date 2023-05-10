package components;

import java.util.Date;

//1.3.3 Creation of the Debit class
public class Debit extends Flow {

	// Constructor
	
	public Debit(String comment, int identifier, int amount, int targetAccountNumber, boolean effect, Date dateOfFlow) {
		super(comment, identifier, amount, targetAccountNumber, effect, dateOfFlow);
	}
}
