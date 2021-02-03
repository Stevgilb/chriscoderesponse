/**
 * 
 */

/**
 * @author techn
 *
 */
import java.util.ArrayList;

public class Accounts {

	/** 
	 * the name of the account
	 */
	
	private String name;
	
	/**
	 * the account id number
	 */
	
	private String uuid;
	
	/**
	 * the user object that owns the account
	 */
	
	private User holder;
	
	/**
	 * the list of transactions for this account
	 */
	
	private ArrayList<Transaction> transactions;
	
		/**
		 * Create a new Account
		 * @param name the account name
		 * @param holder the user object that holds the account
		 * @param theBank the bank that issues the account
		 */
	
	public Accounts(String name, User holder, Bank theBank) {
		// set the account name for the holder
		this.name = name;
		this.holder = holder;
		
		// get new account UUID
		this.uuid = theBank.getNewAccountUUID();
		
		// init transactions to empty ArrayList
		this.transactions = new ArrayList<Transaction>();
		
	}
	
	/**
	 * get the account UUID
	 * @return the UUID
	 */
	public String getUUID() {
		return this.uuid;
	}
	
		/**
		 * Get the summary of the accounts
		 * @return the string summary
		 */
	
	public String getSummaryLine() {
		
		// get the account balance
		double balance = this.getBalance();
		
		//format summary line, depending on if balance is negative
		if (balance >= 0) {
			return String.format("%s : %.02f : %s", this.uuid, balance, this.name);
		} else { return String.format("%s : -%.02f : %s", this.uuid, balance, this.name);
		
	}	
}
		/**
		 * get the balance of account by adding amounts of the transactions
		 * @return the balance value
		 */

	public double getBalance() {
		
		double balance = 0;
		for (Transaction t : this.transactions) {
			balance += t.getAmount();
		}
		return balance;
	}
	
		/**
		 * add a new transaction in this account
		 * @param amount the amount transacted
		 */
	public void addTransaction (double amount) {
		
		//create a new transaction object and add it to our list
		Transaction newTrans = new Transaction(amount, this);
		this.transactions.add(newTrans);
	}
	
		/**
		 * Print the transaction history of the account
		 */
	
	public void printTransHistory() {
		
		System.out.printf("\nTransaction history for account %s\n", this.uuid);
		for (int t = this.transactions.size()-1; t >= 0; t--) {
			System.out.println(this.transactions.get(t).getSummaryLine());
		}
		System.out.println();
	}
	
}
	
