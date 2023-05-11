package components;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

//1.3.3 Creation of the Transfert class
public class Transfert extends Flow{

	// Properties
	
	private int issuerAccountNumber;
	
	// Constructor

	@JsonCreator
	public Transfert(
			@JsonProperty("comment") String comment, 
			@JsonProperty("amount") double amount, 
			@JsonProperty("targetAccountNumber") int targetAccountNumber, 
			@JsonProperty("effect") boolean effect, 
			@JsonProperty("dateOfFlow") LocalDate dateOfFlow, 
			@JsonProperty("issuerAccountNumber") int issuerAccountNumber
	) {
		super(comment, amount, targetAccountNumber, effect, dateOfFlow);
		this.issuerAccountNumber = issuerAccountNumber;
	}
	
	// Getters & Setters

	public int getIssuerAccountNumber() {
		return issuerAccountNumber;
	}
}
