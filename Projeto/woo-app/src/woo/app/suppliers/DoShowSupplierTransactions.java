package woo.app.suppliers;

import pt.tecnico.po.ui.Command;          
import woo.app.exceptions.UnknownSupplierKeyException;                                                                                                    import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;
import woo.Transaction;
import woo.Storefront;                                                                                                                        
import woo.exceptions.UnknownSupplierException;

/**
 * Show all transactions for specific supplier.
 */
public class DoShowSupplierTransactions extends Command<Storefront> {

  private Input<String> _key;

  public DoShowSupplierTransactions(Storefront receiver) {
    super(Label.SHOW_SUPPLIER_TRANSACTIONS, receiver);
    _key = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    try {
      for (Transaction n: _receiver.getSupplierTransactions(_key.value())) {
        _display.addLine(n.toString());
      }
      _display.display();
    } catch(UnknownSupplierException e) {
      throw new UnknownSupplierKeyException(e.getKey());
    }
  }

}
