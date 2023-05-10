package components;

// 1.1.1 Creation of the Client class
public class Client {

	// Properties
	
	private String name;
	private String firstName;
	private int clientNumber;
	private static int totalClient = 0;
	
	// Constructor
	
	public Client(String name, String firstName) {
		this.name = name;
		this.firstName = firstName;
		totalClient++;
		this.clientNumber = totalClient;
	}

	// Getters and Setters
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(int clientNumber) {
		this.clientNumber = clientNumber;
	}
	
	/**
	 * Returns all information about the current client.
	 */
	public String toString() {
		return this.name + " " + this.firstName + " n°" + this.clientNumber; 
	}
	
}
