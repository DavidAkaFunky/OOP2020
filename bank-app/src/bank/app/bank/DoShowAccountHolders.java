package bank.app.bank;

import bank.Bank;
import bank.Holder;
import pt.tecnico.po.ui.Command;

/**
 * Show account holders.
 */
public class DoShowAccountHolders extends Command<Bank> {
  /**
   * @param entity
   */
  public DoShowAccountHolders(Bank entity) {
    super(Labels.SHOW_ACCOUNT_HOLDERS, entity);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    for (Holder h : _receiver.getHolders())
      _display.addLine(h.toString());
    _display.display();
  }
}
