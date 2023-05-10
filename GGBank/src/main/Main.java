package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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
		
		Map<Integer, Account> accountsMap = mappingAccounts(accountCollection);
		displayMapAccounts(accountsMap);
		
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
		arrayToDisplay.stream().forEach(client -> System.out.println(client));;
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
		arrayToDisplay.stream().forEach(account -> System.out.println(account));
	}
	
	public static Map<Integer, Account> mappingAccounts(List<Account> listAccount) {
		Map<Integer, Account> newMappingAccount = new HashMap<>();
		
		listAccount.forEach(account -> newMappingAccount.put(account.getAccountNumber(), account));
		
		return newMappingAccount;
	}
	
	public static void displayMapAccounts(Map<Integer, Account> mapToDisplay) {
		// "entrySet()" returns key-value pairs
		mapToDisplay.entrySet()
		// "stream" allows to use Stream methods on the collection
					.stream()
		// "sorted" to sort the collection. Conversion of "double" to "Double" for use the "compareTo" method of the Double class.
					.sorted(
						(account1, account2) -> ((Double)account1.getValue().getBalance()).compareTo(account2.getValue().getBalance()))
		// Iterate on the collection
					.forEach(account -> System.out.println("Key : " + account.getKey() + ". Value : " + account.getValue()));

	}
}
