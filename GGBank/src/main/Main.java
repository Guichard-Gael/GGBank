package main;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

//1.1.1 Creation of the Main class
public class Main {
	
	public static void main(String[] args) {
		Main mainInstance = new Main();
		
		// 1.1 Clients
		List<Client> clientsCollection = mainInstance.loadClientsCollection(3);
		mainInstance.displayAllClients(clientsCollection);
		
		// 1.2 Accounts
		List<Account> accountsCollection = mainInstance.loadAccountsCollection(clientsCollection);
		mainInstance.displayAllClientAccounts(accountsCollection);
		
		// 1.3 Flows
		Map<Integer, Account> accountsMap = mainInstance.mappingAccounts(accountsCollection);
		mainInstance.displayMapAccounts(accountsMap);
		
		List<Flow> flowsCollection = mainInstance.loadFlowsCollection(accountsCollection);		
		
		mainInstance.applyFlows(flowsCollection, accountsMap);
		
		// 2.1 JSON file of flows
		List<Flow> newFlowsCollection = mainInstance.loadJSONflows();
		mainInstance.applyFlows(newFlowsCollection, accountsMap);
		
		// 2.2 XML file of account
		Map<Integer, Account> newAccountsCollection = mainInstance.loadAccountsCollectionWithXML(clientsCollection);
		mainInstance.displayMapAccounts(newAccountsCollection);
		
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
	 * Create and return a Map with "accountNumber" for the key and the Account instance as value
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
	
	/**
	 * Create instances of Credit, Debit and Transferts classes with json datas
	 * @return List of Flow
	 */
	public List<Flow> loadJSONflows(){
		
		List<Flow> flowsCollection = new ArrayList<>();
		// Get credits.json file
		Path creditsFilePath = Paths.get(".\\src\\datas\\credits.json");
		File jsonCredits = creditsFilePath.toFile();

		// Get debits.json file
		Path debitsFilePath = Paths.get(".\\src\\datas\\debits.json");
		File jsonDebits = debitsFilePath.toFile();
		
		// Get transferts.json file
		Path transfertsFilePath = Paths.get(".\\src\\datas\\transferts.json");
		File jsonTransferts = transfertsFilePath.toFile();
		
		try {
			// Get the object mapper
			ObjectMapper objectMapper = new ObjectMapper();
			// Add a module to accept "LocalDate" type
			objectMapper.registerModule(new JavaTimeModule());
			
			// Create an instance of Debit class with json data.
			Debit debitFlow = objectMapper.readValue(jsonDebits, Debit.class);
			flowsCollection.add(debitFlow);
			
			// Create instances of Credit class with json data.
			Credit[] creditArray = objectMapper.readValue(jsonCredits, Credit[].class);
			Collections.addAll(flowsCollection, creditArray);
			
			// Create an instance of Transfert class with json data.
			Transfert transfertFlow = objectMapper.readValue(jsonTransferts, Transfert.class);
			flowsCollection.add(transfertFlow);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return flowsCollection;
	}
	
	/**
	 * Create and return a Map, from XML file, with "accountNumber" for the key and the Account instance as value
	 * @param clients Collection of clients
	 * @return The new Account collection.
	 */
	public Map<Integer, Account> loadAccountsCollectionWithXML(List<Client> clients) {
		
		// Creating a temporary list
		List<Account> newAccountCollection = new ArrayList<>();
		
		clients.forEach( client -> {
			
			try {
				// Get savingsAccount.xml file
				Path savingsAccountXMLPath = Paths.get(".\\src\\datas\\savingsAccount.xml");
				File savingsAccountXML = savingsAccountXMLPath.toFile();
				
				JAXBContext jaxbContexSavingsAccount = JAXBContext.newInstance(SavingsAccount.class);
				Unmarshaller unmarshallerSavingsAccount = jaxbContexSavingsAccount.createUnmarshaller();
				// Create instance with XML data
				SavingsAccount savingsAccount = (SavingsAccount)unmarshallerSavingsAccount.unmarshal(savingsAccountXML);
				// Set the client to the account
				savingsAccount.setClient(client);
				
				// Get currentAccount.xml file
				Path currentAccountXMLPath = Paths.get(".\\src\\datas\\currentAccount.xml");
				File currentAccountXML = currentAccountXMLPath.toFile();
				
				JAXBContext jaxbContextCurrentAccount = JAXBContext.newInstance(CurrentAccount.class);
				Unmarshaller unmarshallerCurrentAccount = jaxbContextCurrentAccount.createUnmarshaller();
				CurrentAccount currentAccount = (CurrentAccount)unmarshallerCurrentAccount.unmarshal(currentAccountXML);
				currentAccount.setClient(client);
				
				
				newAccountCollection.add(savingsAccount);
				newAccountCollection.add(currentAccount);
				
			} catch(JAXBException e) {
				e.printStackTrace();
			}		
		});

		// Creating the map to return
		Map<Integer, Account> newMappingAccount = new HashMap<>();
		newAccountCollection.forEach(account -> newMappingAccount.put(account.getAccountNumber(), account));
		
		return newMappingAccount;
	}
}
