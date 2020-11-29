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
  /** Input field. */
  private Input<String> _key;

  /** Input field. */
  private Input<String> _title;

  /** Input field. */
  private Input<String> _author;

  /** Input field. */
  private Input<String> _ISBN;

  /** Input field. */
  private Input<Integer> _price;

  /** Input field. */
  private Input<Integer> _cValue;

  /** Input field. */
  private Input<String> _sID;

  /**
   * Constructor.
   * 
   * @param receiver
   */
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

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    try {
      _form.parse();
      _receiver.registerBook(_key.value(), _title.value(), _author.value(), 
                             _ISBN.value(), _price.value(), _cValue.value(), 
                             _sID.value(), 0);
    } catch (DuplicateProductException e) {
      throw new DuplicateProductKeyException(e.getKey());
    } catch (UnknownSupplierException e) {
      throw new UnknownSupplierKeyException(e.getKey());
    }
  }
}
