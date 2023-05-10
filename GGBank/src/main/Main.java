package main;

import java.util.ArrayList;
import java.util.List;

import components.Client;

//1.1.1 Creation of the Main class
public class Main {
	
	public static void main(String[] args) {
		List<Client> clientCollection = loadClientsCollection(3);
		displayAllClients(clientCollection);
	}
	
	/**
	 * Create and return a collection with instances of the Client class
	 * @param numberClients Number of instances needed
	 */
	public static List<Client> loadClientsCollection(int numberClients) {
		
		List<Client> newClientCollection = new ArrayList<>();
		
		for(int index = 1; index <= numberClients; index++) {
			Client newClient = new Client("name" + index, "firstname" + index);
			newClientCollection.add(newClient);
		}
		
		return newClientCollection;
	}
	
	/**
	 * Display all instances of the collection
	 * @param arrayToDisplay The collection to display
	 */
	public static void displayAllClients(List<Client> arrayToDisplay) {
		arrayToDisplay.forEach(client -> System.out.println(client));
	}
}
