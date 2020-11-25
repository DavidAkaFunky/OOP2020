/**
 * 
 */
package bank.app.bank;

import bank.Bank;
import pt.tecnico.po.ui.Command;

/**
 * Show accounts.
 */
public class DoShowAccounts extends Command<Bank> {
  /**
   * @param entity
   */
  public DoShowAccounts(Bank entity) {
    super(Labels.SHOW_ACCOUNTS, entity);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _display.popup(_receiver.getAccounts().toString());
  }
}
