package main;

import java.util.ArrayList;
import java.util.List;

import components.Client;
import components.Account;
import components.CurrentAccount;
import components.SavingsAccount;

//1.1.1 Creation of the Main class
public class Main {
	
	public static void main(String[] args) {
		List<Client> clientCollection = loadClientsCollection(3);
		displayAllClients(clientCollection);
		
		List<Account> accountCollection = loadAccountsCollection(clientCollection);
		displayAllClientAccounts(accountCollection);
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
	
	/**
	 * Create and return a collection with instances of the CurrentAccount and SavingsAccount classes for each client
	 * @param clients Collection of clients
	 */
	public static List<Account> loadAccountsCollection(List<Client> clients) {
		
		List<Account> newAccountCollection = new ArrayList<>();
		
		clients.forEach( client -> {
			SavingsAccount newSavingsAccount = new SavingsAccount("general", client);
			CurrentAccount newCurrentAccount = new CurrentAccount("general", client);
			
			newAccountCollection.add(newSavingsAccount);
			newAccountCollection.add(newCurrentAccount);
		});

		
		return newAccountCollection;
	}
	
	/**
	 * Display all instances of the collection
	 * @param arrayToDisplay The collection to display
	 */
	public static void displayAllClientAccounts(List<Account> arrayToDisplay) {
		arrayToDisplay.forEach(account -> System.out.println(account));
	}
}
