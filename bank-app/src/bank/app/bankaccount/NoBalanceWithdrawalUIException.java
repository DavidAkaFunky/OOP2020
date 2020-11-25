/** @version $Id: NoBalanceWithdrawalUIException.java,v 1.1 2016/09/18 17:31:09 david Exp $ */
package bank.app.bankaccount;

import pt.tecnico.po.ui.DialogException;

/**
 * User interface exception invalid withdrawal operations.
 */
public class NoBalanceWithdrawalUIException extends DialogException {

	/** Serial number. */
	private static final long serialVersionUID = 4928801929604037903L;

	/** Balance. */
	private double _balance;

	/** Amount. */
	private double _amount;

	/**
	 * @param balance
	 * @param amount
	 */
	public NoBalanceWithdrawalUIException(double balance, double amount) {
		_balance = balance;
		_amount = amount;
	}

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
	@Override
	public String getMessage() {
		return Messages.notEnoughBalance(_balance, _amount);
	}

}

// $Log: NoBalanceWithdrawalUIException.java,v $
// Revision 1.1  2016/09/18 17:31:09  david
// Finished the port to po-uuilib. Probably, some refactoring is still needed.
//
// Revision 1.2  2013/08/30 00:02:44  david
// Adapted bank project to the new po-uilib (with validity checking in the command interface). Performed minor cleanup.
//
// Revision 1.1 2011/09/25 14:49:10 david
// New version of the bank application. The textui version has been updated
// and now uses exceptions as a bridge to convey to the menu class errors
// in operations.
//