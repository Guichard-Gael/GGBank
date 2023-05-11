package components;

import java.time.LocalDate;

//1.3.3 Creation of the Transfert class
public class Transfert extends Flow{

	// Properties
	
	private int issuerAccountNumber;
	
	// Constructor

	public Transfert(String comment, double amount, int targetAccountNumber, boolean effect, LocalDate dateOfFlow, int issuerAccountNumber) {
		super(comment, amount, targetAccountNumber, effect, dateOfFlow);
		this.issuerAccountNumber = issuerAccountNumber;
	}
	
	// Getters & Setters

	public int getIssuerAccountNumber() {
		return issuerAccountNumber;
	}
}
