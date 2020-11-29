package woo.app.suppliers;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes
import woo.exceptions.UnknownSupplierException;
import woo.app.exceptions.UnknownSupplierKeyException;

/**
 * Enable/disable supplier transactions.
 */
public class DoToggleTransactions extends Command<Storefront> {
  /** Input field. */
  private Input<String> _key;

  /**
   * Constructor.
   * 
   * @param receiver
   */
  public DoToggleTransactions(Storefront receiver) {
    super(Label.TOGGLE_TRANSACTIONS, receiver);
    _key = _form.addStringInput(Message.requestSupplierKey());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public void execute() throws DialogException {
    try {
      _form.parse();
      _display.popup(_receiver.toggleSupplierTransactions(_key.value()) ? Message.transactionsOn(_key.value()) : Message.transactionsOff(_key.value()));
    } catch (UnknownSupplierException e) {
      throw new UnknownSupplierKeyException(e.getKey());
    }
  }

}
