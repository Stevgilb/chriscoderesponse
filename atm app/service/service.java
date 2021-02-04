package service;

import java.util.Scanner;

import calc.calculator;

public class service {

		// TODO Auto-generated constructor stub

	    String customerName;
	    String customerId;

	    public service (String name, String id) {

	        customerName = name;
	        customerId = id;
	    }
		
		
		
		
		private void withdrawsav(int amount3) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		private void withdraw(int amount4) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		private void depositsav(int amount2) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		private void deposit(int amount) {
			// TODO Auto-generated method stub
			
		}





		public void showMenu() {
			// TODO Auto-generated method stub
	        Scanner scanner = new Scanner(System.in);

	        System.out.println("Welcome "+customerName);
	        System.out.println("Your ID: "+customerId);
	        System.out.println("\n");
	        System.out.println("1. Check Balance");
	        System.out.println("2. Saving Balance");
	        System.out.println("3. Deposit");
	        System.out.println("4. Deposit Savings");
	        System.out.println("5. Withdraw");
	        System.out.println("6. Withdraw Savings");
	        System.out.println("7. Exit");
	        
	        do {
	            System.out.println("Enter an option");
	            char option = scanner.next().charAt(0);
	            System.out.println("\n");
	            {
	        //error is on line directly under this one
	            switch(option) {

	            case '1':
	                String balance;
					System.out.println("Balance = " + balance);
	                System.out.println("\n");
	                break;
	                
	            case '2':
	                String bals;
					System.out.println("Bals = " + bals);
	                System.out.println("\n");
	                break;

	            case '3':
	                System.out.println("Enter an amount to deposit: ");
	                int amount = scanner.nextInt();
	                deposit(amount);
	                System.out.println("\n");
	                break;

	            case '4':
	                System.out.println("Enter an amount to deposit: ");
	                int amount2 = scanner.nextInt();
	                depositsav(amount2);
	                System.out.println("\n");
	                break;

	            case '5':
	                System.out.println("Enter an amount to withdraw: ");
	                int amount4 = scanner.nextInt();
	                withdraw(amount4);
	                System.out.println("\n");
	                break;

	            case '6':
	                System.out.println("Enter an amount to withdraw: ");
	                int amount3 = scanner.nextInt();
	                withdrawsav(amount3);
	                System.out.println("\n");
	                break;

	            case '7':
	                System.out.println("*****************************************************");
	                break;

	            default:
	                System.out.println("Invalid option: Please try again");
	                break;

	                

	                
	            
	        }  while(option != '7') ;
	        System.out.println("Thank you for using our services");
	        
	        scanner.close();
	        
	        }	

}
