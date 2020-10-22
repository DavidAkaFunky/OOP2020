package woo.app.products;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

/**
 * Register box.
 */
public class DoRegisterProductBox extends Command<Storefront> {

  Input<String> _supplierID;
  Input<String> _productID;
  Input<Integer> _price;
  Input<Integer> _criticalLevel;
  Input<String> _serviceType;

  public DoRegisterProductBox(Storefront receiver) {
    super(Label.REGISTER_BOX, receiver);
    //FIXME init input fields
  }

  @Override
  public final void execute() throws DialogException {
    //FIXME implement command
  }
}
