package components;

import javax.xml.bind.annotation.XmlRootElement;

//1.2.2 Creation of the CurrentAccount class
@XmlRootElement(name="currentaccount")
public class CurrentAccount extends Account {

	// Constructor
	
	public CurrentAccount() {
		super();
	}
	
	public CurrentAccount(String label, Client client) {
		super(label, client);
	}
}
