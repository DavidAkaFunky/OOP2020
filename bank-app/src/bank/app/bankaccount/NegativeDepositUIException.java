/** @version $Id: NegativeDepositUIException.java,v 1.1 2016/09/18 17:31:09 david Exp $ */
package bank.app.bankaccount;

import pt.tecnico.po.ui.DialogException;

/**
 * User interface exception invalid withdrawal operations.
 */
public class NegativeDepositUIException extends DialogException {

	/** Serial number. */
	private static final long serialVersionUID = 4928801929604037903L;

	/** Value. */
	private double _amount;

	/**
	 * @param amount
	 */
	public NegativeDepositUIException(double amount) {
		_amount = amount;
	}

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
	@Override
	public String getMessage() {
		return Messages.invalidNegativeAmountDeposit(_amount);
	}

}
