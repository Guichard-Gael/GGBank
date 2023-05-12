package components;

import java.util.Comparator;
import javax.xml.bind.annotation.XmlElement;

//1.2.1 Creation of the Account class

public abstract class Account implements Comparator<Account> {
	
	// Properties
	protected String label;
	protected double balance = 0;
	protected int accountNumber;
	protected Client client;
	protected static int totalAccount = 0;
	
	// Constructor
	
	protected Account() {
		totalAccount++;
		this.accountNumber = totalAccount;
	}

	protected Account(String label, Client client) {
		this();
		this.label = label;
		this.client = client;
	}

	// Getters and Setters
	
	public String getLabel() {
		return label;
	}

	@XmlElement(name="label")
	public void setLabel(String label) {
		this.label = label;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(Flow flow) {
		if(flow instanceof Credit) {
			this.balance += flow.getAmount();
		}
		else if(flow instanceof Transfert) {
			// Conversion to use the method "getIssuerAccountNumber"
			Transfert transfertFlow = (Transfert)flow;
			if(this.accountNumber == transfertFlow.getTargetAccountNumber()) {
				this.balance += transfertFlow.getAmount();
			}
			else if(this.accountNumber == transfertFlow.getIssuerAccountNumber()) {
				this.balance -= transfertFlow.getAmount();				
			}
		}
		else {
			this.balance -= flow.getAmount();			
		}
		
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public String toString() {
		return "Account n°" + this.accountNumber + ", balance : " + this.balance + ", label : " + this.label + ", client : " + this.client;
	}

	@Override
	public int compare(Account account1, Account account2) {
		
		return ((Double)account1.balance).compareTo(account2.balance);
	}
}
