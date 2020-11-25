package woo.app.clients;

import java.util.Map.Entry;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.Client;
import woo.Storefront;

/**
 * Show all clients.
 */
public class DoShowAllClients extends Command<Storefront> {

  public DoShowAllClients(Storefront storefront) {
    super(Label.SHOW_ALL_CLIENTS, storefront);
  }

  @Override
  public void execute() throws DialogException {
    for (Client c: _receiver.getClients()) {
      _display.addLine(c.toString());
    }
    _display.display();
  }
}
