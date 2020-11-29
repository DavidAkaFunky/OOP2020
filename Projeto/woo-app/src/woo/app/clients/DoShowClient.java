package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exceptions.UnknownClientKeyException;
import woo.exceptions.UnknownClientException;
import woo.Client;
import woo.Notification;
import woo.Storefront;

/**
 * Show client.
 */
public class DoShowClient extends Command<Storefront> {
  /** Input field. */
  private Input<String> _key;

  /**
   * Constructor.
   * 
   * @param storefront
   */
  public DoShowClient(Storefront storefront) {
    super(Label.SHOW_CLIENT, storefront);
    _key = _form.addStringInput(Message.requestClientKey());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public void execute() throws DialogException {
    try {
      _form.parse();
      Client client = _receiver.getClient(_key.value());
      _display.addLine(client.toString());
      for (Notification n: client.getNotifications()) {
        _display.addLine(n.toString());
      }
      client.clearNotifications();
      _display.display();
    } catch (UnknownClientException e) {
      throw new UnknownClientKeyException(e.getKey());
    }
  }

}
