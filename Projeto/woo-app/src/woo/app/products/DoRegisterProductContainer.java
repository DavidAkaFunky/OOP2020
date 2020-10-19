package woo.app.products;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

/**
 * Register container.
 */
public class DoRegisterProductContainer extends Command<Storefront> {

  Input<Integer> _supplierID;
  Input<Integer> _productID;
  Input<Integer> _price;
  Input<Integer> _criticalLevel;
  Input<String> _serviceType;
  Input<String> _serviceLevel;

  public DoRegisterProductContainer(Storefront receiver) {
    super(Label.REGISTER_CONTAINER, receiver);
    //FIXME init input fields
  }

  @Override
  public final void execute() throws DialogException {
    //FIXME implement command
  }
}
