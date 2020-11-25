package bank.app.bank;

import bank.Bank;
import bank.exceptions.DuplicateHolderException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;

/**
 * Class for representing creation of account holders.
 */
public class DoCreateHolder extends Command<Bank> {
  /** Input field. */
  Input<String> _name;

  /** Input field. */
  Input<Integer> _id;

  /**
   * @param bank
   */
  public DoCreateHolder(Bank bank) {
    super(Labels.CREATE_HOLDER, bank);
    _name = _form.addStringInput(Messages.requestHolderName());
    _id = _form.addIntegerInput(Messages.requestHolderId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _form.parse();
    try {
      _receiver.addHolder(_name.value(), _id.value());
      _display.popup(Messages.createdHolder(_name.value(), _id.value()));
    } catch (DuplicateHolderException e) {
      _display.popup("DUPLICATE WHILE TRYING; " + Messages.createdHolder(_name.value(), _id.value()));
    }
  }
}
