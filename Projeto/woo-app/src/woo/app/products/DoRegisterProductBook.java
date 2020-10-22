package woo.app.products;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

/**
 * Register book.
 */
public class DoRegisterProductBook extends Command<Storefront> {

  Input<String> _supplierID;
  Input<String> _productID;
  Input<Integer> _price;
  Input<Integer> _criticalLevel;
  Input<String> _title;
  Input<String> _author;
  Input<String> _isbn;

  public DoRegisterProductBook(Storefront receiver) {
    super(Label.REGISTER_BOOK, receiver);
    //FIXME init input fields
  }

  @Override
  public final void execute() throws DialogException {
    //FIXME implement command
  }
}
