package woo.app.transactions;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

/**
 * Register order.
 */
public class DoRegisterOrderTransaction extends Command<Storefront> {

  private Input<String> _supplierID;
  private Input<String> _productID;
  private Input<Integer> _qty;

  public DoRegisterOrderTransaction(Storefront receiver) {
    super(Label.REGISTER_ORDER_TRANSACTION, receiver);
    _supplierID = _form.addStringInput(Message.requestSupplierKey());
    _productID = _form.addStringInput(Message.requestProductKey());
    _qty = _form.addIntegerInput(Message.requestAmount());
  }

  @Override
  public final void execute() throws DialogException {
    //FIXME implement command
  }

}
