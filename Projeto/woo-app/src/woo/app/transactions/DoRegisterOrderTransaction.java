package woo.app.transactions;

import java.util.Collections;
import java.util.Map;
import java.util.LinkedHashMap;

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
import woo.Storefront;

/**
 * Register order.
 */
public class DoRegisterOrderTransaction extends Command<Storefront> {
  /** Input field. */
  private Input<String> _supplierKey;

  /** Input field. */
  private Input<String> _productKey;

  /** Input field. */
  private Input<Integer> _productQty;

  /** Input field. */
  private Input<Boolean> _moreProducts;

  /** LinkedHashMap structure that contains product ID's and its quantities to be added to order.
   * (Keys are ordered by their insertion order). */
  private Map<String, Integer> _products = new LinkedHashMap<String, Integer>();

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
    _supplierKey = _form.addStringInput(Message.requestSupplierKey());
    _productKey = _form.addStringInput(Message.requestProductKey());
    _productQty = _form.addIntegerInput(Message.requestAmount());
    _moreProducts = _form.addBooleanInput(Message.requestMore());
    try {
      _form.parse();
      _form.clear();
      _products.put(_productKey.value(), _productQty.value());
      while (_moreProducts.value() == true) {
        _productKey = _form.addStringInput(Message.requestProductKey());
        _productQty = _form.addIntegerInput(Message.requestAmount());
        _moreProducts = _form.addBooleanInput(Message.requestMore());
        _form.parse();
        _form.clear();
        // Add stock or create product
        var productQty = _products.get(_productKey.value());
        if (productQty == null) {
          _products.put(_productKey.value(), _productQty.value());
        } else {
          _products.put(_productKey.value(), productQty + _productQty.value());
        }
      }
      _receiver.registerOrderTransaction(_supplierKey.value(), Collections.unmodifiableMap(_products));
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
