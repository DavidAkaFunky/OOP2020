/**
 * 
 */
package bank.app.holder;

/**
 * @author Programação com Objectos
 * @version 2.1
 */
@SuppressWarnings("nls")
public class Messages {

	/**
	 * @return prompt for account
	 */
	public static final String requestAccount() {
		return "Número de conta? ";
	}

	/**
	 * @return prompt for name
	 */
	public static final String requestName() {
		return "Nome? ";
	}

	/**
	 * @param id the duplicate holder's id.
	 * @return error string
	 */
	public static final String duplicateHolder(int id) {
		return "Um cliente com a identificação " + id + " já existe.";
	}
}
