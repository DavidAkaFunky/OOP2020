package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.Client;
import woo.Storefront;

/**
 * Show all clients.
 */
public class DoShowAllClients extends Command<Storefront> {
  /**
   * Constructor.
   * 
   * @param storefront
   */
  public DoShowAllClients(Storefront storefront) {
    super(Label.SHOW_ALL_CLIENTS, storefront);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public void execute() throws DialogException {
    for (Client c: _receiver.getClients()) {
      _display.addLine(c.toString());
    }
    _display.display();
  }
}
