package woo.app.suppliers;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes
import woo.exceptions.UnknownSupplierException;
import woo.app.exceptions.UnknownSupplierKeyException;

/**
 * Enable/disable supplier transactions.
 */
public class DoToggleTransactions extends Command<Storefront> {

  private Input<String> _key;

  public DoToggleTransactions(Storefront receiver) {
    super(Label.TOGGLE_TRANSACTIONS, receiver);
    _key = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    String key = _key.value();
    try {
      String state = _receiver.toggleSupplierTransactions(key) ? Message.transactionsOn(key) : Message.transactionsOff(key);
      _display.addLine(state);
      _display.display();
    } catch (UnknownSupplierException e) {
      throw new UnknownSupplierKeyException(e.getKey());
    }
  }

}
