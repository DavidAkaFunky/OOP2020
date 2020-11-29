package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input; 
import woo.app.exceptions.DuplicateProductKeyException;
import woo.app.exceptions.UnknownServiceTypeException;
import woo.app.exceptions.UnknownSupplierKeyException;
import woo.exceptions.DuplicateProductException;
import woo.exceptions.UnknownServTypeException;
import woo.exceptions.UnknownSupplierException;
import woo.Storefront;

/**
 * Register box.
 */
public class DoRegisterProductBox extends Command<Storefront> {
  /** Input field. */
  Input<String> _key;

  /** Input field. */
  Input<Integer> _price;

  /** Input field. */
  Input<Integer> _cValue;

  /** Input field. */
  Input<String> _sID;

  /** Input field. */
  Input<String> _sType;

  /**
   * Constructor.
   * 
   * @param receiver
   */
  public DoRegisterProductBox(Storefront receiver) {
    super(Label.REGISTER_BOX, receiver);
    _key = _form.addStringInput(Message.requestProductKey());
    _price = _form.addIntegerInput(Message.requestPrice());
    _cValue = _form.addIntegerInput(Message.requestStockCriticalValue());
    _sID = _form.addStringInput(Message.requestSupplierKey());
    _sType = _form.addStringInput(Message.requestServiceType());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    try {
      _form.parse();
      _receiver.registerBox(_key.value(), _price.value(), _cValue.value(), 
                             _sID.value(), _sType.value(), 0);
    } catch (DuplicateProductException e) {
      throw new DuplicateProductKeyException(e.getKey());
    } catch (UnknownSupplierException e) {
      throw new UnknownSupplierKeyException(e.getKey());
    } catch (UnknownServTypeException e) {
      throw new UnknownServiceTypeException(e.getType());
    }
  }
}
