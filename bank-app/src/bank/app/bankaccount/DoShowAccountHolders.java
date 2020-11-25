package bank.app.bankaccount;

import bank.BankAccount;
import bank.Holder;
import pt.tecnico.po.ui.Command;

/**
 * This class represents a command for showing all the holders of an account.
 */
public class DoShowAccountHolders extends Command<BankAccount> {
	/**
	 * Constructor.
	 * 
	 * @param account
	 *            target account.
	 */
	public DoShowAccountHolders(BankAccount account) {
		super(Labels.SHOW_ACCOUNT_HOLDERS, account);
	}

  /** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() {
		for (Holder h: _receiver.getHolders())
		  _display.addLine(h.toString());
		_display.display();
	}
}
