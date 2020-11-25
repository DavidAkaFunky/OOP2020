package bank.app.savingsaccount;

/**
 * @author Programação com Objectos
 */
@SuppressWarnings("nls")
public class Messages {

	/**
	 * @param balance
	 *            the account's balance
	 * @return string showing the account's balance
	 */
	public static final String showBalance(double balance) {
		return "Saldo corrente: " + balance;
	}
}
