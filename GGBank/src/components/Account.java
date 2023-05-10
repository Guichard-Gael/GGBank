package components;

import java.util.Comparator;

//1.2.1 Creation of the Account class
public abstract class Account implements Comparator<Account> {
	protected String label;
	protected double balance;
	protected int accountNumber;
	protected Client client;
	protected static int totalAccount = 0;
	
	protected Account(String label, Client client) {
		totalAccount++;
		this.label = label;
		this.client = client;
		this.accountNumber = totalAccount;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double amount) {
		this.balance = amount;
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
