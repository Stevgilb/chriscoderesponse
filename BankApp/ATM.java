/**
 * 
 */

/**
 * @author techn
 *
 */

import java.util.Scanner;

public class ATM {


	public static void main(String[] args) {
		
		// init scanner
		Scanner sc = new Scanner(System.in);
		
		// init bank
		Bank theBank = new Bank("Bank of Dogecoin");
		
		// add a user which also creates a savings account
		User aUser = theBank.addUser("john", "doe", "1234");
		
		// add a checcking account for the user
		Accounts newAccount = new Accounts("Checking", aUser, theBank);
		aUser.addAccount(newAccount);
		theBank.addAccount(newAccount);
		
		User curUser;
		while (true) {
			
			// stay in log in promt till success
			curUser = ATM.mainMenuPrompt(theBank, sc);
			
			// stay in main menu untill user quits
			ATM.printUserMenu(curUser, sc);
			
		}
		
	}
	
		/**
		 * Printing the ATM login menu
		 * @param theBank the bank object whose account to use
		 * @param sc the scanner object for input
		 * @return the authUser object
		 */
	
	public static User mainMenuPrompt(Bank theBank, Scanner sc) {
		
		//inits
		String userID;
		String pin;
		User authUser;
		
		// prompt user for user ID/pin combo until success
		
		do {
			
			System.out.printf("Welcome to %s", theBank.getName());
			System.out.print("Enter User ID:");
			userID = sc.nextLine();
			System.out.print("Enter User Pin:");
			pin = sc.nextLine();
			
			// try to get the user object corresponding to ID/Pin combo
			authUser = theBank.userLogin(userID, pin);
			if (authUser == null) {
				System.out.println("Incorrect ID or Pin Entered");
			}
			
		} while(authUser == null); // continue looping till successful 
		
		return authUser;
	}
	
	public static void printUserMenu(User theUser, Scanner sc) {
		
		// print summary of Users Accounts
		
		theUser.printAccountSummary();
		
		// init
		int choice;
		
		// user menu
		do {
			
			System.out.printf("Welcome %s, what would you like to do?\n", theUser.getFirstName());
			System.out.println(" 1. Show Account trans Hist");
			System.out.println(" 2. Withdraw");
			System.out.println(" 3. Deposit");
			System.out.println(" 4. Transfer");
			System.out.println(" 5. Exit");
			System.out.println();
			System.out.println("Enter Choice ");
			choice = sc.nextInt();
			if (choice <1 || choice >5) {
				System.out.println("Invalid choice, Please Choose Again.");
			}
		} while(choice <1 || choice > 5);
		
		// process the choice
		switch (choice) {
		
		case 1:
			ATM.showTransHistory(theUser, sc);
			break;
		case 2:
			ATM.withdrawFunds(theUser, sc);
			break;
		case 3:
			ATM.depositFunds(theUser, sc);
			break;
		case 4:
			ATM.transferFunds(theUser, sc);
			break;
		case 5:
			// gobble up rest of previous input
			sc.nextLine();
			break;
		
		}
		
		// redisplay the menu unless user wants to Exit
		if (choice != 5) {
			ATM.printUserMenu(theUser, sc);
		}
		
	}

		/**
		 * show the transaction history for an account
		 * @param theUser the logged in User object
		 * @param sc the Scanner object used for user input
		 */
	public static void showTransHistory(User theUser, Scanner sc) {
		
		int theAcct;
		
		// get account trans history
		do {
			System.out.printf("Enter the number (1-%d) of teh account\n" + "whose transactions you want to see:",
					theUser.numAccounts());
			theAcct = sc.nextInt()-1;
			if (theAcct <0 || theAcct >= theUser.numAccounts()) {
				System.out.println("Invalid Account");
			}
		}while (theAcct <0 || theAcct >= theUser.numAccounts());
		
		//print transaction history
		theUser.printAcctTransHistory(theAcct);
		
	}

	public static void transferFunds(User theUser, Scanner sc) {
		
		//inits
		int fromAcct;
		int toAcct;
		double amount;
		double acctBal;
		
		// get the account to transfer from
		do {
			System.out.printf("Enter the number (1-%d) of the account\n" + "To transfer from: " , theUser.numAccounts());
			fromAcct = sc.nextInt()-1;
			if (fromAcct <0 || fromAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account");
			}
		} while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
		acctBal = theUser.getAcctBalance(fromAcct);
		
		// get the account to transfer to
		do {
			System.out.printf("Enter the number (1-%d) of the account\n" + "To transfer to: " , theUser.numAccounts());
			toAcct = sc.nextInt()-1;
			if (toAcct <0 || toAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account");
			} while (toAcct < 0 || toAcct >= theUser.numAccounts());
			
			//get the amount to transfer
			do {
				System.out.printf("enter the amount to transfer: ", "max %.02f): ", acctBal);
				amount = sc.nextDouble();
				if (amount < 0) {
					System.out.println("Amount must be greater than zero");
				} else if (amount > acctBal) {
					System.out.printf("Amount must not be greater than\n balance of %.02f. \n", acctBal);
				}
			} while (amount < 0 || amount > acctBal);
		
				// finally do the transfer
				theUser.addAcctTransaction(fromAcct, -1*amount, String.format(
						"Transfer to account %s", theUser.getAcctUUID(toAcct)));
				
				theUser.addAcctTransaction(toAcct, amount, String.format(
						"Transfer to account %s", theUser.getAcctUUID(fromAcct))); 
		}
				
	}
		
			/**
			 * process a fund withdraw from an account
			 * @param theUser the logged in User object
			 * @param sc the Scanner object for user input
			 */
		
		public static void withdrawFunds(User theUser, Scanner sc) {
			
			//inits
			int fromAcct;
			double amount;
			double acctBal;
			
			// get the account to transfer from
			do {
				System.out.printf("Enter the number (1-%d) of the account\n" + "To transfer from: " , theUser.numAccounts());
				fromAcct = sc.nextInt()-1;
				if (fromAcct <0 || fromAcct >= theUser.numAccounts()) {
					System.out.println("Invalid account");
				}
			} while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
			acctBal = theUser.getAcctBalance(fromAcct);
			
			//get amount to transfer
			do {
				System.out.printf("enter the amount to withdraw from: ", "max %.02f): ", acctBal);
				amount = sc.nextDouble();
				if (amount < 0) {
					System.out.println("Amount must be greater than zero");
				} else if (amount > acctBal) {
					System.out.printf("Amount must not be greater than\n balance of %.02f. \n", acctBal);
				}
			} while (amount < 0 || amount > acctBal);
			
			// gobble up rest of previous input
			sc.nextLine();
		
			// do the withdraw
			theUser.addAcctTransaction(fromAcct, -1*amount, null);
	}

		/**
		 * process a fund deposit to an account
		 * @param theUser the logged in User object
		 * @param sc the Scanner object for User input
		 */
		
		public static void depositFunds(User theUser, Scanner sc) {
			
			//inits
			int toAcct;
			double amount;
			double acctBal;
			
			// get the account to transfer from
			do {
				System.out.printf("Enter the number (1-%d) of the account\n" + "To Deposit in: " , theUser.numAccounts());
				toAcct = sc.nextInt()-1;
				if (toAcct <0 || toAcct >= theUser.numAccounts()) {
					System.out.println("Invalid account");
				}
			} while (toAcct < 0 || toAcct >= theUser.numAccounts());
			acctBal = theUser.getAcctBalance(toAcct);
			
			//get amount to transfer
			do {
				System.out.printf("enter the amount to transfer: ", "max %.02f): ", acctBal);
				amount = sc.nextDouble();
				if (amount < 0) {
					System.out.println("Amount must be greater than zero");
				}
			} while (amount < 0 );
			
			// gobble up rest of previous input
			sc.nextLine();
		
			// do the deposit
			theUser.addAcctTransaction(toAcct, amount, null);
			
		}
}


