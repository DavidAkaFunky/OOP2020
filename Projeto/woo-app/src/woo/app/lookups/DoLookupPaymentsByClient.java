package woo.app.lookups;

import pt.tecnico.po.ui.Command;
import woo.app.exceptions.UnknownClientKeyException;
import woo.exceptions.UnknownClientException;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;                                                                                                                
import woo.Storefront;                                                                                                                        

/**
 * Lookup payments by given client.
 */
public class DoLookupPaymentsByClient extends Command<Storefront> {

  private Input<String> _key;

  public DoLookupPaymentsByClient(Storefront storefront) {
    super(Label.PAID_BY_CLIENT, storefront);
    _key = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    try {
      for (var s: _receiver.lookupPaymentsByClient(_key.value())) {
        _display.addLine(s.toString());
      }
      _display.display();
    } catch (UnknownClientException e) {
      throw new UnknownClientKeyException(e.getKey());
    }
  }

}
