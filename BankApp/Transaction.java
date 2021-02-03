import java.util.Date;


public class Transaction {

	/**
	 * the amount of this transaction
	 */
	
private double amount;

	/**
	 * the timestamp of this transaction
	 */

private Date timestamp;

	/**
	 * the memo of this transaction
	 */

private String memo;

	/**
	 * the account which made the transaction
	 */

private Accounts inAccount;

	/**
	 * create a new transaction
	 * @param amount the amount of transaction
	 * @param inAccount the account the transaction belongs to
	 */

public Transaction(double amount, Accounts inAccount) {
	
	this.amount = amount;
	this.inAccount = inAccount;
	this.timestamp = new Date();
}

	/**
	 * get the amount of the transaction
	 * @return the amount
	 */
public double getAmount() {
	return this.amount;
}

	/**
	 * get the string summary of the transaction
	 * @return the summary string
	 */

public String getSummaryLine() {
	if (this.amount >= 0) {
		return String.format("%s : %.02f", this.timestamp.toString(), this.amount);
	} else {
		return String.format("%s : -%.02f", this.timestamp.toString(), this.amount);
	}
}


}
