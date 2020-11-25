/**
 * 
 */
package bank.app.savingsaccount;

import bank.SavingsAccount;
import pt.tecnico.po.ui.Command;

/**
 * Show the account's balance.
 */
public class DoShowBalance extends Command<SavingsAccount> {
  /**
   * @param entity
   */
  public DoShowBalance(SavingsAccount entity) {
    super(Labels.SHOW_BALANCE, entity);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _display.popup(Messages.showBalance(_receiver.getBalance()));
  }
}
