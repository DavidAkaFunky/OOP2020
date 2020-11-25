package bank.app.bank;

import bank.Bank;
import bank.BankAccount;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;

/**
 * Class for representing account creation.
 */
public class DoCreateAccount extends Command<Bank> {
  /** Input field.  */
  Input<Float> _amount;

  /**
   * Constructor.
   * 
   * @param bank
   */
  public DoCreateAccount(Bank bank) {
    super(Labels.CREATE_ACCOUNT, bank);
    _amount = _form.addFloatInput(Messages.requestInitialBalance());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _form.parse();
    BankAccount account = _receiver.createAccount(_amount.value());
    _display.popup(Messages.accountCreated(account.getId()));
  }
}
