package woo.app.suppliers;

import java.util.Map.Entry;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import java.util.Map.Entry;
import woo.Supplier;
import woo.Storefront;

/**
 * Show all suppliers.
 */
public class DoShowSuppliers extends Command<Storefront> {

  public DoShowSuppliers(Storefront receiver) {
    super(Label.SHOW_ALL_SUPPLIERS, receiver);
  }

  @Override
  public void execute() throws DialogException {
    for (Supplier s: _receiver.getSuppliers()) {
      String active = s.isActive() ? Message.yes() : Message.no();
      _display.addLine(s.toString() + active);
    }
    _display.display();
  }
}
