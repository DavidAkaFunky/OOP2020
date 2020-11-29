package woo.app.transactions;

import java.util.Collections;
import java.util.TreeMap;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exceptions.UnauthorizedSupplierException;
import woo.app.exceptions.UnknownProductKeyException;
import woo.app.exceptions.UnknownSupplierKeyException;
import woo.app.exceptions.WrongSupplierException;
import woo.exceptions.InactiveSupplierException;
import woo.exceptions.IncorrectSupplierException;
import woo.exceptions.UnknownProductException;
import woo.exceptions.UnknownSupplierException;
import woo.Order;
import woo.Storefront;

/**
 * Register order.
 */
public class DoRegisterOrderTransaction extends Command<Storefront> {
  /** Input field. */
  private Input<String> _supplierID;

  /** Input field. */
  private Input<String> _productID;

  /** Input field. */
  private Input<Integer> _qty;

  /** Input field. */
  private Input<Boolean> _choice;

  /** TreeMap structure that contains product ID's and its quantities to be added to order. */
  private TreeMap<String, Integer> _products = new TreeMap<String, Integer>();

  /**
   * Constructor.
   * 
   * @param receiver
   */
  public DoRegisterOrderTransaction(Storefront receiver) {
    super(Label.REGISTER_ORDER_TRANSACTION, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _products.clear(); // Clear structure after each order
    _supplierID = _form.addStringInput(Message.requestSupplierKey());
    _productID = _form.addStringInput(Message.requestProductKey());
    _qty = _form.addIntegerInput(Message.requestAmount());
    _choice = _form.addBooleanInput(Message.requestMore());
    try {
      _form.parse();
      _form.clear();
      _products.put(_productID.value(), _qty.value());
      while (_choice.value() == true) {
        _productID = _form.addStringInput(Message.requestProductKey());
        _qty = _form.addIntegerInput(Message.requestAmount());
        _choice = _form.addBooleanInput(Message.requestMore());
        _form.parse();
        _form.clear();
        _products.put(_productID.value(), _qty.value());
      }
      _receiver.registerOrderTransaction(_supplierID.value(), Collections.unmodifiableMap(_products));
    } catch (UnknownSupplierException e) {
        throw new UnknownSupplierKeyException(e.getKey());
    } catch (UnknownProductException e) {
        throw new UnknownProductKeyException(e.getKey());
    }  catch (IncorrectSupplierException e) {
        throw new WrongSupplierException(e.getSupplierKey(), e.getProductKey());
    } catch (InactiveSupplierException e) {
        throw new UnauthorizedSupplierException(e.getKey());
    }
  }

}
