package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exceptions.UnknownClientKeyException;
import woo.app.exceptions.UnknownProductKeyException;
import woo.exceptions.UnknownClientException;
import woo.exceptions.UnknownProductException;
import woo.Storefront;

/**
 * Toggle product-related notifications.
 */
public class DoToggleProductNotifications extends Command<Storefront> {
  /** Input field. */
  private Input<String> _pID;
  
  /** Input field. */
  private Input<String> _cID;

  /**
   * Constructor.
   * 
   * @param storefront
   */
  public DoToggleProductNotifications(Storefront storefront) {
    super(Label.TOGGLE_PRODUCT_NOTIFICATIONS, storefront);
    _pID = _form.addStringInput(Message.requestProductKey());
    _cID = _form.addStringInput(Message.requestClientKey());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public void execute() throws DialogException {
    try {
      _form.parse();
      _receiver.changeClientProductNotifications(_pID.value(), _cID.value());
    } catch (UnknownProductException e) {
      throw new UnknownProductKeyException(e.getKey());
    } catch (UnknownClientException e) {
      throw new UnknownClientKeyException(e.getKey());
    }
  }

}
