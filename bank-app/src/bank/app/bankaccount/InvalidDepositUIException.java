/** @version $Id: InvalidDepositUIException.java,v 1.1 2016/09/18 17:31:09 david Exp $ */
/*
 * $Log: InvalidDepositUIException.java,v $
 * Revision 1.1  2016/09/18 17:31:09  david
 * Finished the port to po-uuilib. Probably, some refactoring is still needed.
 *
 * Revision 1.2  2013/08/30 00:02:44  david
 * Adapted bank project to the new po-uilib (with validity checking in the command interface). Performed minor cleanup.
 *
 * Revision 1.1  2011/09/25 14:49:10  david
 * New version of the bank application. The textui version has been updated
 * and now uses exceptions as a bridge to convey to the menu class errors
 * in operations.
 *
 * 
 */
package bank.app.bankaccount;

import pt.tecnico.po.ui.DialogException;

/**
 * User interface exception invalid withdrawal operations.
 */
public class InvalidDepositUIException extends DialogException {

	/** Serial number. */
	private static final long serialVersionUID = 4928801929604037903L;

	/** Constructor. */
	public InvalidDepositUIException() {
		// EMPTY
	}

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
	@Override
	public String getMessage() {
		return Messages.invalidDeposit();
	}
	
}
