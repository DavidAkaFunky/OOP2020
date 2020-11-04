package woo.app.suppliers;

import woo.Supplier;
import woo.Storefront;
import java.util.Map.Entry;
import pt.tecnico.po.ui.Command; 
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

/**
 * Show all suppliers.
 */
public class DoShowSuppliers extends Command<Storefront> {

  public DoShowSuppliers(Storefront receiver) {
    super(Label.SHOW_ALL_SUPPLIERS, receiver);
  }

  @Override
  public void execute() throws DialogException {
    for (Entry<String, Supplier> s: _receiver.getSuppliers())
      _display.addLine(s.getValue().toString());
    _display.display();
  }
}
