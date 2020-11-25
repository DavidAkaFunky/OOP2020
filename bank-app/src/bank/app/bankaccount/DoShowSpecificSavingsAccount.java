package bank.app.bankaccount;

import bank.BankAccount;
import bank.SavingsAccount;
import bank.app.savingsaccount.BankSavingsAccountMenu;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Menu;

/**
 * Class for showing a specific savings account.
 */
public class DoShowSpecificSavingsAccount extends Command<BankAccount> {
  /** Input field. */
  Input<Integer> _id;

  /**
   * Constructor.
   * 
   * @param account
   */
  public DoShowSpecificSavingsAccount(BankAccount account) {
    super(Labels.SHOW_SAVINGS_ACCOUNT, account);
    _id = _form.addIntegerInput(Messages.requestSavingsAccountId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _form.parse();
    SavingsAccount account = _receiver.getSavingsAccount(_id.value());
    if (account != null) {
      Menu m = new BankSavingsAccountMenu(account);
      m.open();
    } else {
      _display.popup(Messages.errorShowingSavingsAccount());
    }
  }
}
