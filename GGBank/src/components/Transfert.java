package components;

import java.util.Date;

//1.3.3 Creation of the Transfert class
public class Transfert extends Flow{

	// Properties
	
	private int issuerAccountNumber;
	
	// Constructor

	public Transfert(String comment, int identifier, int amount, int targetAccountNumber, boolean effect, Date dateOfFlow, int issuerAccountNumber) {
		super(comment, identifier, amount, targetAccountNumber, effect, dateOfFlow);
		this.issuerAccountNumber = issuerAccountNumber;
	}
}
