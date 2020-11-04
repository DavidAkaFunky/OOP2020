package woo.app.clients;

import woo.Client;
import woo.Storefront;
import java.util.Map.Entry;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;                                                                                                      
import pt.tecnico.po.ui.Input;                                                                                                                

/**
 * Show all clients.
 */
public class DoShowAllClients extends Command<Storefront> {

  public DoShowAllClients(Storefront storefront) {
    super(Label.SHOW_ALL_CLIENTS, storefront);
  }

  @Override
  public void execute() throws DialogException {
    for (Entry<String, Client> c: _receiver.getClients())
      _display.addLine(c.getValue().toString());
    _display.display();
  }
}
