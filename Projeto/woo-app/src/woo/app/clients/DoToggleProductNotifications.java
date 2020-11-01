package woo.app.clients;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes
import woo.app.exceptions.UnknownClientException;
import woo.app.exceptions.UnknownProductException;
import woo.exceptions.UnknownClientKeyException;
import woo.exceptions.UnknownProductKeyException;

/**
 * Toggle product-related notifications.
 */
public class DoToggleProductNotifications extends Command<Storefront> {

  private Input<String> _pID;
  private Input<String> _cID;

  public DoToggleProductNotifications(Storefront storefront) {
    super(Label.TOGGLE_PRODUCT_NOTIFICATIONS, storefront);
    _pID = _form.addStringInput(Message.requestProductKey());
    _cID = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    try {
      _receiver.toggleClientProductNotifications(_pID.value(), _cID.value());
    } catch (UnknownClientKeyException e) {
      throw new UnknownClientException(_cID.value());
    } catch (UnknownProductKeyException e) {
      throw new UnknownProductException(_pID.value());
    }
  }

}
