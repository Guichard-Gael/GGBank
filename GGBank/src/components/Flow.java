package components;

import java.time.LocalDate;

//1.3.2 Creation of the Flow class
public abstract class Flow {

	// Properties
	
	private String comment;
	private int identifier;
	private double amount;
	private int targetAccountNumber;
	private boolean effect;
	private LocalDate dateOfFlow;
	private static int totalFlow = 0;
	
	// Constructor
	
	protected Flow(String comment, double amount, int targetAccountNumber, boolean effect, LocalDate dateOfFlow) {
		totalFlow++;
		this.comment = comment;
		this.identifier = totalFlow;
		this.amount = amount;
		this.targetAccountNumber = targetAccountNumber;
		this.effect = effect;
		this.dateOfFlow = dateOfFlow;
	}
	
	// Getters and Setters
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getIdentifier() {
		return identifier;
	}
	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getTargetAccountNumber() {
		return targetAccountNumber;
	}
	public void setTargetAccountNumber(int targetAccountNumber) {
		this.targetAccountNumber = targetAccountNumber;
	}
	public boolean isEffect() {
		return effect;
	}
	public void setEffect(boolean effect) {
		this.effect = effect;
	}
	public LocalDate getDateOfFlow() {
		return dateOfFlow;
	}
	public void setDateOfFlow(LocalDate dateOfFlow) {
		this.dateOfFlow = dateOfFlow;
	}
}
