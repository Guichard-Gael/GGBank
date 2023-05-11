package components;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

//1.3.3 Creation of the Flow class
public class Credit extends Flow{

	// Constructor
	
	@JsonCreator
	public Credit(
			@JsonProperty("comment") String comment, 
			@JsonProperty("amount") double amount, 
			@JsonProperty("targetAccountNumber") int targetAccountNumber, 
			@JsonProperty("effect") boolean effect, 
			@JsonProperty("dateOfFlow") LocalDate dateOfFlow
	) {
		super(comment, amount, targetAccountNumber, effect, dateOfFlow);
	}
	
	public String toString() {
		return "comment : " + this.getComment()
				+ ", identifier : " + this.getIdentifier()
				+ ", amount : " + this.getAmount()
				+ ", targetAccountNumber : " + this.getTargetAccountNumber()
				+ ", effect : " + this.isEffect()
				+ ", dateOfFlow : " + this.getDateOfFlow();
	}
}
