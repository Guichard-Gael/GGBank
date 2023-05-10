package components;

import java.util.Date;

//1.3.3 Creation of the Flow class
public class Credit extends Flow{

	// Constructor
	
	public Credit(String comment, int identifier, int amount, int targetAccountNumber, boolean effect, Date dateOfFlow) {
		super(comment, identifier, amount, targetAccountNumber, effect, dateOfFlow);
	}
}
