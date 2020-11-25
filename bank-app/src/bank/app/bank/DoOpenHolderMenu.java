package bank.app.bank;

import bank.Bank;
import bank.Holder;
import bank.app.holder.BankHolderMenu;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Menu;

/**
 * Open account holder menu.
 */
public class DoOpenHolderMenu extends Command<Bank> {
  /** Input field. */
  Input<Integer> _id;

  /**
   * Constructor.
   * 
   * @param bank
   */
  public DoOpenHolderMenu(Bank bank) {
    super(Labels.HOLDER_MENU, bank);
    _id = _form.addIntegerInput(Messages.requestHolderId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _form.parse();
    Holder holder = _receiver.getHolder(_id.value());

    if (holder != null) {
      Menu m = new BankHolderMenu(holder);
      m.open();
    }
    else {
      _display.popup(Messages.noSuchHolder(_id.value()));
    }
  }
}
