package components;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

//1.3.3 Creation of the Debit class
public class Debit extends Flow {

	// Constructor
	
	@JsonCreator
	public Debit(
			@JsonProperty("comment") String comment, 
			@JsonProperty("amount") double amount, 
			@JsonProperty("targetAccountNumber") int targetAccountNumber, 
			@JsonProperty("effect") boolean effect, 
			@JsonProperty("dateOfFlow") LocalDate dateOfFlow
	) {
		super(comment, amount, targetAccountNumber, effect, dateOfFlow);
	}
}
