/**
 * 
 */

/**
 * @author techn
 *
 */
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

		/**
		 * The first name of the user.
		 */
	
	private String firstName;

		/**
		 * The last name of the user.
		 */

	private String lastName;
	
		/**
		 * The id of the user.
		 */

	private String uuid;

		/**
		 * The MD5 of the user's pin number
		 */

	private byte pinHash[];

		/**
		 * The list of accounts for this user
		 */

	private ArrayList<Accounts> accounts;
	
	public String getFirstName( ) {
		return this.firstName;
	}
	
	public String getLastName( ) {
		return this.lastName;
	}
	
		/**
		 * Print summaries of the Users accounts
		 */
	public void printAccountSummary() {
		
		System.out.printf("%'s accounts summary", this.firstName);
		for (int a = 0; a < this.accounts.size(); a++) {
			System.out.printf("  %d) %s\n", a+1, this.accounts.get(a).getSummaryLine());
		}
		System.out.println();
	}
	
		/**
		 * get the number of accounts of the user
		 * @returnthe number of accounts
		 */
	public int numAccounts() {
		return this.accounts.size();
	}
	
		/**
		 * print the transaction history for the account
		 * @param acctIdx the index of the account to use
		 */
	public void printAcctTransHistory(int acctIdx) {
		this.accounts.get(acctIdx).printTransHistory();
	}
	
		/**
		 * get the balance of the particular account
		 * @param acctIdx the index of the account to use
		 * @return the balance of the account
		 */
	
	public double getAcctBalance(int acctIdx) {
		return this.accounts.get(acctIdx).getBalance();
	}
	
		/**
		 * get the UUID of a particular account
		 * @param acctIdx the index of the account to use
		 * @return the UUID of the account
		 */
	
	public String getAcctUUID(int acctIdx) {
		return this.accounts.get(acctIdx).getUUID();
	}
	
	
		/**
		 * add a transaction to a particular account
		 * @param acctIdx the index of the account
		 * @param amount the amount of the transaction
		 * @param string 
		 */
	public void addAcctTransaction (int acctIdx, double amount, String string) {
		this.accounts.get(acctIdx).addTransaction(amount);
	}
	
	/**
	 * Creating a new user
	 * @param fristName first name of the user
	 * @param lastName last name of the user
	 * @param pin pin of the users account
	 * @param theBank the bank object of the user customer
	 */


public User(String firstName, String lastName, String pin, Bank theBank) {
	
		// set users name
		this.firstName = firstName;
		this.lastName = lastName;
		
		// store the pins MD5, THAN ORIGINAL PIN VALUE
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		// get a new, UUID for the user
		this.uuid = theBank.getNewUserUUID();
		
		// create empty list of accounts
		this.accounts = new ArrayList<Accounts>();
		
		// print the log message
		System.out.printf("New user %s, %s with ID %s created.\n", lastName, firstName, this.uuid);
	
}

	/**
	 * Adding an account for the user
	 * @param anAcct the account to add
	 */
	public void addAccount(Accounts anAcct) {
		this.accounts.add(anAcct);
	}
	
	/**
	 * return the user's UUID
	 * @return the UUID
	 */
	public String getUUID() {
		return this.uuid;
	}
	
	/**
	 * Check whether a pin matches the true User pin
	 * @param apin the pin to check
	 * @return whether the pin is valid or not
	 */
	
	public boolean validatePin(String apin) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(apin.getBytes()), 
					this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		return false;
		
	}
}

