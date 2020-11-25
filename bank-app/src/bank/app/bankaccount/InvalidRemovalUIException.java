/** @version $Id: InvalidRemovalUIException.java,v 1.1 2016/09/18 17:31:09 david Exp $ */
package bank.app.bankaccount;

import pt.tecnico.po.ui.DialogException;

/**
 * User interface exception invalid withdrawal operations.
 */
public class InvalidRemovalUIException extends DialogException {

	/** Serial number. */
	private static final long serialVersionUID = 4928801929604037903L;

	/** Account number. */
	private int _account;

	/** Balance. */
	private double _amount;

	/**
	 * @param account
	 * @param amount
	 */
	public InvalidRemovalUIException(int account, double amount) {
		_account = account;
		_amount = amount;
	}

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
	@Override
	public String getMessage() {
		return bank.app.bank.Messages.errorRemovingAccount(_account, _amount);
	}

}
