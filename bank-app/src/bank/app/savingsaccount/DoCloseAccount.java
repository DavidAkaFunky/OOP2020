/**
 * 
 */
package bank.app.savingsaccount;

import bank.SavingsAccount;
import pt.tecnico.po.ui.Command;

/**
 * Close this account.
 */
public class DoCloseAccount extends Command<SavingsAccount> {
  /**
   * @param entity
   */
  public DoCloseAccount(SavingsAccount entity) {
    super(Labels.CLOSE, entity);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _receiver.close();
  }
}
