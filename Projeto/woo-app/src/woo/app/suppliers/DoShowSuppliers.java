package woo.app.suppliers;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.Supplier;
import woo.Storefront;

/**
 * Show all suppliers.
 */
public class DoShowSuppliers extends Command<Storefront> {
  /**
   * Constructor.
   * 
   * @param receiver
   */
  public DoShowSuppliers(Storefront receiver) {
    super(Label.SHOW_ALL_SUPPLIERS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public void execute() throws DialogException {
    for (Supplier s: _receiver.getSuppliers()) {
      String active = s.isActive() ? Message.yes() : Message.no();
      _display.addLine(s.toString() + active);
    }
    _display.display();
  }
}
