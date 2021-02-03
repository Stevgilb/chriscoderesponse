/**
 * 
 */

/**
 * @author techn
 *
 */
import java.util.ArrayList;
import java.util.Random;


public class Bank {

	private String name;
	
	private ArrayList<User> users;

	private ArrayList<Accounts> accounts;

	/**
	 * Create a new Bank object with empty lists of users and accounts
	 * @param name the name of the bank
	 */
	
	public Bank(String name) {
		
		this.name = name;
		this.users = new ArrayList<User>();
		this.accounts = new ArrayList<Accounts>();
		
	}
	
		/**
		 * Gets the name of the bank
		 * @return The name of the bank
		 */
	
	public String getName() {
		return this.name;
	}
	
	

	/**
	 * generate a new UUID for a user
	 * @return the UUID
	 */
		public String getNewUserUUID() {
		
		//inits
		String uuid;
		Random rng = new Random();
		int len = 4;
		boolean nonUnique;
		
		//continue to loop until we get unique ID
		do {
			
			// generate the number
			uuid = "";
			for(int c = 0; c< len; c++) {
				uuid += ((Integer)rng.nextInt(10)).toString();
			}
			
			// check if unique
			nonUnique = false;
			for (User u : this.users) {
				if (uuid.compareTo(u.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}
			
		} while (nonUnique);
		
		return uuid;
		
	}
	
	/**
	 * generate a new UUID for an account
	 * @return the UUID
	 */
	
	public String getNewAccountUUID() {
		
		//inits
		String uuid;
		Random rng = new Random();
		int len = 10;
		boolean nonUnique;
		
		//continue to loop until we get unique ID
		do {
			
			// generate the number
			uuid = "";
			for(int c = 0; c< len; c++) {
				uuid += ((Integer)rng.nextInt(10)).toString();
			}
			
			// check if unique
			nonUnique = false;
			for (Accounts a : this.accounts) {
				if (uuid.compareTo(a.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}
			
		} while (nonUnique);
		
		return uuid;
		
		
	}
	
		/**
		 * Add an account
		 * @param anAcct the account to add
		 */
	
	public void addAccount(Accounts anAcct) {
		this.accounts.add(anAcct);
	}
	
	/**
	 * Create a new user of the bank
	 * @param firstName the users first name
	 * @param lastName the users last name
	 * @param pin the users pin
	 * @return the new User object
	 */
	
	public User addUser(String firstName, String lastName, String pin) {
		
		// create a new user object and add it to the list
		User newUser = new User(firstName, lastName, pin, this);
		this.users.add(newUser);
		
		// create a savings account for the user and add user and bank accounts list
		Accounts newAccount = new Accounts("Savings" , newUser, this);
		newUser.addAccount(newAccount);
		this.accounts.add(newAccount);
		
		return newUser;
	}
	
		/**
		 * get the user object associated with userID and pin, if they are valid
		 * @param userID the UUID of the user to log in
		 * @param pin the pin of the user
		 * @return the User object, if the login is successful, or null if not
		 */
	
	public User userLogin(String userID,String pin) {
		
		//search through list of users
		for (User u : this.users) {
			
			// check user ID is correct
			if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
				return u;
			}
		}
		// no user was found or incorrect pin
		return null;
		
	}
}
