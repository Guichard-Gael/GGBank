package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import components.Account;
import components.Client;
import components.Credit;
import components.CurrentAccount;
import components.Debit;
import components.Flow;
import components.SavingsAccount;
import components.Transfert;

//1.1.1 Creation of the Main class
public class Main {
	
	public static void main(String[] args) {
		List<Client> clientCollection = loadClientsCollection(3);
		displayAllClients(clientCollection);
		
		List<Account> accountCollection = loadAccountsCollection(clientCollection);
		displayAllClientAccounts(accountCollection);
		
		Map<Integer, Account> accountsMap = mappingAccounts(accountCollection);
		displayMapAccounts(accountsMap);
		
		List<Flow> flowCollection = loadFlowsCollection(accountCollection);
	}
	
	/**
	 * Create and return a collection with instances of the Client class
	 * @param numberClients Number of instances needed
	 * @return The new Client collection.
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
		arrayToDisplay.stream().forEach(client -> System.out.println(client));
	}
	
	/**
	 * Create and return a collection with instances of the CurrentAccount and SavingsAccount classes for each client
	 * @param clients Collection of clients
	 * @return The new Account collection.
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
	
	/**
	 * Create and return a Map with "accountNumber" for the key and the Account instance for the value
	 * @param listAccount
	 * @return The new Map.
	 */
	public static Map<Integer, Account> mappingAccounts(List<Account> listAccount) {
		Map<Integer, Account> newMappingAccount = new HashMap<>();
		
		listAccount.forEach(account -> newMappingAccount.put(account.getAccountNumber(), account));
		
		return newMappingAccount;
	}
	
	/**
	 * Displays all Key-Value of the Map.
	 * @param mapToDisplay The Map to displays
	 */
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
	
	/**
	 * Creates and returns a collection containing the instances of Flow
	 * @param accountCollection Collection of accounts
	 * @return The new Flow collection
	 */
	public static List<Flow> loadFlowsCollection(List<Account> accountCollection) {
		List<Flow> flowCollection = new ArrayList<>();
		
		// Get the date after 2 days.
		LocalDate afterTwoDays = LocalDate.now().plusDays(2);
		
		Debit newDebit = new Debit("essence", 50, 1, true, afterTwoDays);
		flowCollection.add(newDebit);
		
		Transfert newTransfert = new Transfert("present", 50, 1, true, afterTwoDays, 2);
		flowCollection.add(newTransfert);
		
		// Credit for all CurrentAccount instances
		accountCollection.stream()
					.filter(account -> account instanceof CurrentAccount)
					.forEach(account -> {
						Credit newCredit = new Credit("prime", 100.50, account.getAccountNumber(), true, afterTwoDays);
						flowCollection.add(newCredit);
					});
		
		// Credit for all SavingsAccount instances
		accountCollection.stream()
					.filter(account -> account instanceof SavingsAccount)
					.forEach(account -> {
						Credit newCredit = new Credit("prime", 1500, account.getAccountNumber(), true, afterTwoDays);
						flowCollection.add(newCredit);
					});
		
		return flowCollection;
	}
}
