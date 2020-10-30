package woo.app.clients;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes
import woo.app.exceptions.UnknownClientKeyException;
import woo.app.exceptions.UnknownProductKeyException;

/**
 * Show all transactions for a specific client.
 */
public class DoShowClientTransactions extends Command<Storefront> {

  private Input<String> _key;

  public DoShowClientTransactions(Storefront storefront) {
    super(Label.SHOW_CLIENT_TRANSACTIONS, storefront);
    _key = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    try {
      _display.addLine(_receiver.showClientTransactions(_key.value()));
      _display.display();
    } catch (UnknownClientKeyException e) {
      throw new UnknownClientKeyException(_key.value());
    }
  }

}
