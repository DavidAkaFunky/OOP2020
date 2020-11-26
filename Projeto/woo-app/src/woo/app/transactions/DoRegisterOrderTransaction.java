package woo.app.transactions;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes
import woo.app.exceptions.UnauthorizedSupplierException;
import woo.app.exceptions.UnknownProductKeyException;
import woo.app.exceptions.UnknownSupplierKeyException;
import woo.app.exceptions.WrongSupplierException;
import woo.exceptions.InactiveSupplierException;
import woo.exceptions.IncorrectSupplierException;
import woo.exceptions.UnknownProductException;
import woo.exceptions.UnknownSupplierException;

/**
 * Register order.
 */
public class DoRegisterOrderTransaction extends Command<Storefront> {

  private Input<String> _supplierID;
  private Input<String> _productID;
  private Input<Integer> _qty;
  private Input<String> _choice;

  public DoRegisterOrderTransaction(Storefront receiver) {
    super(Label.REGISTER_ORDER_TRANSACTION, receiver);
    _supplierID = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public final void execute() throws DialogException {
    do {
      _productID = _form.addStringInput(Message.requestProductKey());
      _qty = _form.addIntegerInput(Message.requestAmount());
      _choice = _form.addStringInput(Message.requestMore());
      _form.parse(false);
      try {
        _receiver.registerOrderTransaction(_supplierID.value(), _productID.value(), _qty.value());
      } catch (UnknownSupplierException e) {
        throw new UnknownSupplierKeyException(e.getKey());
      } catch (UnknownProductException e) {
        throw new UnknownProductKeyException(e.getKey());
      }  catch (IncorrectSupplierException e) {
        throw new WrongSupplierException(e.getSupplierKey(), e.getProductKey());
      } catch (InactiveSupplierException e) {
        throw new UnauthorizedSupplierException(e.getKey());
      }
    } while (_choice.value().equals("s"));
  }

}
