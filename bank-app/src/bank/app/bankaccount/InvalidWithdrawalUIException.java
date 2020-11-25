/** @version $Id: InvalidWithdrawalUIException.java,v 1.1 2016/09/18 17:31:09 david Exp $ */
package bank.app.bankaccount;

import pt.tecnico.po.ui.DialogException;

/**
 * User interface exception invalid withdrawal operations.
 */
public class InvalidWithdrawalUIException extends DialogException {

	/** Serial number. */
	private static final long serialVersionUID = 4928801929604037903L;

	/** Error message. */
	private String _message;

	/**
	 * @param message
	 */
	public InvalidWithdrawalUIException(String message) {
		_message = message;
	}

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
	@Override
	public String getMessage() {
		return _message;
	}

}
