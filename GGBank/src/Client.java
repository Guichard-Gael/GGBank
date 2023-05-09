// 1.1.1 Creation of the client class

public class Client {

	// Properties
	
	private String name;
	private String firstName;
	private int clientNumber;
	
	// Constructor
	
	Client(String name, String firstName) {
		this.name = name;
		this.firstName = firstName;
		this.clientNumber++;
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
		return "The client is " + this.name + " " + this.firstName + " and the number of the client is n°" + this.clientNumber; 
	}
	
}
