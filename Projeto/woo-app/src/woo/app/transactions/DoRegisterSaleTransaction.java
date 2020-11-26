package woo.app.transactions;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes
import woo.exceptions.NoEnoughStockProductException;
import woo.exceptions.UnavailableFileException;
import woo.exceptions.UnknownClientException;
import woo.exceptions.UnknownProductException;
import woo.app.exceptions.UnknownClientKeyException;
import woo.app.exceptions.UnknownProductKeyException;
import woo.app.exceptions.UnavailableProductException;

/**
 * Register sale.
 */
public class DoRegisterSaleTransaction extends Command<Storefront> {

  private Input<String> _clientID;
  private Input<String> _productID;
  private Input<Integer> _limitDate;
  private Input<Integer> _qty;

  public DoRegisterSaleTransaction(Storefront receiver) {
    super(Label.REGISTER_SALE_TRANSACTION, receiver);
    _clientID = _form.addStringInput(Message.requestClientKey());
    _limitDate = _form.addIntegerInput(Message.requestPaymentDeadline());
    _productID = _form.addStringInput(Message.requestProductKey());
    _qty = _form.addIntegerInput(Message.requestAmount());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try {
      _receiver.registerSaleTransaction(_clientID.value(), _limitDate.value(), _productID.value(), _qty.value());
    } catch (UnknownClientException e) {
      throw new UnknownClientKeyException(e.getKey());
    } catch (UnknownProductException e) {
      throw new UnknownProductKeyException(e.getKey());
    } catch (NoEnoughStockProductException e) {
      throw new UnavailableProductException(e.getKey(), e.getRequested(), e.getAvailable());
    }
  }

}
