package calc;

public class calculator {

	
	int balance;
	int bals;
	int amount;
	int amount2;
	int amount4;
	int amount3;
    
	void withdraw(int amount4) {
		if (amount4 != 0) {
            balance = balance - amount4;
		}
		
	}
	
	void deposit(int amount) {
		if (amount <= 0) {
			balance = balance + amount;
		}
		
	}
	
	void withdrawsav(int amount3) {
		if (amount3 != 0) {
            bals = bals - amount3;
		}
		
	}

	void depositsav(int amount2) {
		if (amount2 <= 0) {
			bals = bals + amount2;
		}
		
	}
	
}


