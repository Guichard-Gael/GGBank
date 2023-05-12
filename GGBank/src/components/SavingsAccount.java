package components;

import javax.xml.bind.annotation.XmlRootElement;

//1.2.2 Creation of the SavingsAccount class
@XmlRootElement(name="savingsaccount")
public class SavingsAccount extends Account {

	// Constructor
	
	public SavingsAccount() {
		super();
	}
	
	public SavingsAccount(String label, Client client) {
		super(label, client);
	}
}
