package woo.app.suppliers;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exceptions.UnknownSupplierKeyException;
import woo.exceptions.UnknownSupplierException;
import woo.Transaction;
import woo.Storefront;

/**
 * Show all transactions for specific supplier.
 */
public class DoShowSupplierTransactions extends Command<Storefront> {
  /** Input field. */
  private Input<String> _key;

  /**
   * Constructor.
   * 
   * @param receiver
   */
  public DoShowSupplierTransactions(Storefront receiver) {
    super(Label.SHOW_SUPPLIER_TRANSACTIONS, receiver);
    _key = _form.addStringInput(Message.requestSupplierKey());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public void execute() throws DialogException {
    try {
      _form.parse();
      for (Transaction n: _receiver.getSupplierTransactions(_key.value())) {
        _display.addLine(n.toString());
      }
      _display.display();
    } catch(UnknownSupplierException e) {
      throw new UnknownSupplierKeyException(e.getKey());
    }
  }

}
