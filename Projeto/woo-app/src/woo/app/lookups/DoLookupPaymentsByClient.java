package woo.app.lookups;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exceptions.UnknownClientKeyException;
import woo.exceptions.UnknownClientException;
import woo.Sale;                                                                                         
import woo.Storefront;                                                                                                                        

/**
 * Lookup payments by given client.
 */
public class DoLookupPaymentsByClient extends Command<Storefront> {
  /** Input field. */
  private Input<String> _key;

  /**
   * Constructor.
   * 
   * @param storefront
   */
  public DoLookupPaymentsByClient(Storefront storefront) {
    super(Label.PAID_BY_CLIENT, storefront);
    _key = _form.addStringInput(Message.requestClientKey());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public void execute() throws DialogException {
    try {
      _form.parse();
      for (Sale s: _receiver.lookupPaymentsByClient(_key.value())) {
        _display.addLine(s.toString());
      }
      _display.display();
    } catch (UnknownClientException e) {
      throw new UnknownClientKeyException(e.getKey());
    }
  }

}
