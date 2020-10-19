package woo.app.clients;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

/**
 * Register new client.
 */
public class DoRegisterClient extends Command<Storefront> {

  Input<Integer> _id;
  Input<String> _name;
  Input<String> _address;

  public DoRegisterClient(Storefront storefront) {
    super(Label.REGISTER_CLIENT, storefront);
    //FIXME init input fields
  }

  @Override
  public void execute() throws DialogException {
    //FIXME implement command
  }

}
