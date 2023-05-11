package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
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
		Main mainInstance = new Main();
		
		List<Client> clientCollection = mainInstance.loadClientsCollection(3);
		mainInstance.displayAllClients(clientCollection);
		
		List<Account> accountCollection = mainInstance.loadAccountsCollection(clientCollection);
		mainInstance.displayAllClientAccounts(accountCollection);
		
		Map<Integer, Account> accountsMap = mainInstance.mappingAccounts(accountCollection);
		mainInstance.displayMapAccounts(accountsMap);
		
		List<Flow> flowCollection = mainInstance.loadFlowsCollection(accountCollection);		
		
		mainInstance.applyFlows(flowCollection, accountsMap);
	}
	
	/**
	 * Create and return a collection with instances of the Client class
	 * @param numberClients Number of instances needed
	 * @return The new Client collection.
	 */
	public List<Client> loadClientsCollection(int numberClients) {
		
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
	public void displayAllClients(List<Client> arrayToDisplay) {
		arrayToDisplay.stream().forEach(System.out::println);
	}
	
	/**
	 * Create and return a collection with instances of the CurrentAccount and SavingsAccount classes for each client
	 * @param clients Collection of clients
	 * @return The new Account collection.
	 */
	public List<Account> loadAccountsCollection(List<Client> clients) {
		
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
	public void displayAllClientAccounts(List<Account> arrayToDisplay) {
		arrayToDisplay.stream().forEach(System.out::println);
	}
	
	/**
	 * Create and return a Map with "accountNumber" for the key and the Account instance for the value
	 * @param listAccount
	 * @return The new Map.
	 */
	public Map<Integer, Account> mappingAccounts(List<Account> listAccount) {
		Map<Integer, Account> newMappingAccount = new HashMap<>();
		
		listAccount.forEach(account -> newMappingAccount.put(account.getAccountNumber(), account));
		
		return newMappingAccount;
	}
	
	/**
	 * Displays all Key-Value of the Map.
	 * @param mapToDisplay The Map to displays
	 */
	public void displayMapAccounts(Map<Integer, Account> mapToDisplay) {
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
	public List<Flow> loadFlowsCollection(List<Account> accountCollection) {
		List<Flow> flowCollection = new ArrayList<>();
		
		// Get the date after 2 days.
		LocalDate afterTwoDays = LocalDate.now().plusDays(2);
		
		Debit newDebit = new Debit("essence", 50, 1, true, afterTwoDays);
		flowCollection.add(newDebit);
		
		// Credit for all CurrentAccount instances
		accountCollection.stream()
					.filter(CurrentAccount.class::isInstance)
					.forEach(account -> {
						Credit newCredit = new Credit("prime", 100.50, account.getAccountNumber(), true, afterTwoDays);
						flowCollection.add(newCredit);
					});
		
		// Credit for all SavingsAccount instances
		accountCollection.stream()
					.filter(SavingsAccount.class::isInstance)
					.forEach(account -> {
						Credit newCredit = new Credit("prime", 1500, account.getAccountNumber(), true, afterTwoDays);
						flowCollection.add(newCredit);
					});
		
		Transfert newTransfert = new Transfert("present", 50, 2, true, afterTwoDays, 1);
		flowCollection.add(newTransfert);
		
		return flowCollection;
	}
	
	/**
	 * Apply all flows
	 * @param flowsCollection The collection of flows
	 * @param accountsMap The Map of accounts
	 */
	public void applyFlows(List<Flow> flowsCollection, Map<Integer, Account> accountsMap) {

		// Iterate on the flowsCollection
		flowsCollection.stream().forEach(flow -> {

			// Check the type of the flow
			if(flow instanceof Transfert) {
				Transfert transfertFlow = (Transfert)flow;
				Account targetAccount = accountsMap.get(transfertFlow.getTargetAccountNumber());
				Account issuerAccount = accountsMap.get(transfertFlow.getIssuerAccountNumber());
				targetAccount.setBalance(transfertFlow);
				issuerAccount.setBalance(transfertFlow);
			}
			else {
				Account flowAccount = accountsMap.get(flow.getTargetAccountNumber());
				flowAccount.setBalance(flow);
			}
		});
		Predicate<Account> isNegative = account -> account.getBalance() < 0;
		// Display all account with negative balance.
		flowsCollection.stream()
						.filter(flow -> isNegative.test(accountsMap.get(flow.getTargetAccountNumber())))
						.forEach( flow -> System.out.println(accountsMap.get(flow.getTargetAccountNumber())));
		
		this.displayMapAccounts(accountsMap);
	}
}
