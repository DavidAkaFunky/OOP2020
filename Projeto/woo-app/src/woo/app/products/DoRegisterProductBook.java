package woo.app.products;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes
import woo.exceptions.DuplicateProductException;
import woo.app.exceptions.DuplicateProductKeyException;
import woo.exceptions.UnknownSupplierException;
import woo.app.exceptions.UnknownSupplierKeyException;

/**
 * Register book.
 */
public class DoRegisterProductBook extends Command<Storefront> {

  private Input<String> _key;
  private Input<String> _title;
  private Input<String> _author;
  private Input<String> _ISBN;
  private Input<Integer> _price;
  private Input<Integer> _cValue;
  private Input<String> _sID;

  public DoRegisterProductBook(Storefront receiver) {
    super(Label.REGISTER_BOOK, receiver);
    _key = _form.addStringInput(Message.requestProductKey());
    _title = _form.addStringInput(Message.requestBookTitle());
    _author = _form.addStringInput(Message.requestBookAuthor());
    _ISBN = _form.addStringInput(Message.requestISBN());
    _price = _form.addIntegerInput(Message.requestPrice());
    _cValue = _form.addIntegerInput(Message.requestStockCriticalValue());
    _sID = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try {
      _receiver.registerBook(_key.value(), _title.value(), _author.value(), 
                             _ISBN.value(), _price.value(), _cValue.value(), 
                             _sID.value(), 1);
    } catch (DuplicateProductException e) {
      throw new DuplicateProductKeyException(_key.value());
    } catch (UnknownSupplierException e) {
      throw new UnknownSupplierKeyException(_sID.value());
    }
  }
}
