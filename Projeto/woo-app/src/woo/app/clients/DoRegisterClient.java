package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exceptions.DuplicateClientKeyException;
import woo.exceptions.DuplicateClientException;
import woo.Storefront;

/**
 * Register new client.
 */
public class DoRegisterClient extends Command<Storefront> {
  /** Input field. */
  private Input<String> _key;

  /** Input field. */
  private Input<String> _name;

  /** Input field. */
  private Input<String> _address;

  /**
   * Constructor.
   * 
   * @param storefront
   */
  public DoRegisterClient(Storefront storefront) {
    super(Label.REGISTER_CLIENT, storefront);
    _key = _form.addStringInput(Message.requestClientKey());
    _name = _form.addStringInput(Message.requestClientName());
    _address = _form.addStringInput(Message.requestClientAddress());
  }
  
  /** @see pt.tecnico.po.ui.Command#execute() */ 
  @Override
  public void execute() throws DialogException {
    try {
      _form.parse();
      _receiver.registerClient(_key.value(), _name.value(), _address.value());
    } catch (DuplicateClientException e) {
      throw new DuplicateClientKeyException(e.getKey());
    }
  }

}
