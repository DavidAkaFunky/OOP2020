package bank.app.bankaccount;

import java.util.List;

import bank.BankAccount;
import bank.SavingsAccount;
import pt.tecnico.po.ui.Command;

/**
 * This class represents a command for showing all the savings accounts.
 */
public class DoShowAllSavingsAccounts extends Command<BankAccount> {

	/**
	 * Constructor.
	 * 
	 * @param account
	 *            the target account.
	 */
	public DoShowAllSavingsAccounts(BankAccount account) {
		super(Labels.SHOW_ALL_SAVINGS_ACCOUNTS, account);
	}

  /** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() {
		List<SavingsAccount> accounts = _receiver.getSavingsAccounts();
		if (accounts != null)
			_display.popup(accounts.toString());
		else
			_display.popup(Messages.errorShowingSavingsAccounts());
	}
}
