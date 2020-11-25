package bank.app.bank;

import bank.Bank;
import bank.BankAccount;
import bank.app.bankaccount.BankAccountMenu;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Menu;

/**
 * Open account menu.
 */
public class DoOpenAccountMenu extends Command<Bank> {
  
  /** Input field.  */
  Input<Integer> _id;
  
	/**
	 * Constructor.
	 * 
	 * @param bank
	 */
	public DoOpenAccountMenu(Bank bank) {
		super(Labels.ACCOUNT_MENU, bank);
		_id = _form.addIntegerInput(Messages.requestAccountId());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() {
		_form.parse();
		BankAccount account = _receiver.getAccount(_id.value());

		if (account != null) {
      Menu m = new BankAccountMenu(account, _receiver);
      m.open();
		}
		else {
      _display.popup(Messages.errorSelectingAccount(_id.value()));
		}
	}
}
