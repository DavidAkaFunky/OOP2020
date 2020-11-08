package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes
import woo.app.exceptions.UnknownClientKeyException;
import woo.exceptions.UnknownClientException;
import woo.Client;
import woo.Storefront;

/**
 * Show client.
 */
public class DoShowClient extends Command<Storefront> {

  private Input<String> _key;

  public DoShowClient(Storefront storefront) {
    super(Label.SHOW_CLIENT, storefront);
    _key = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    try {
      Client client = _receiver.getClient(_key.value());
      _display.addLine(client.toString());
      _display.display();
    } catch (UnknownClientException e) {
      throw new UnknownClientKeyException(_key.value());
    }
  }

}
