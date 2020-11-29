package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.DialogException;
import woo.app.exceptions.DuplicateProductKeyException;
import woo.app.exceptions.UnknownSupplierKeyException;
import woo.app.exceptions.UnknownServiceLevelException;
import woo.app.exceptions.UnknownServiceTypeException;
import woo.exceptions.DuplicateProductException;
import woo.exceptions.UnknownSupplierException;
import woo.exceptions.UnknownServLevelException;
import woo.exceptions.UnknownServTypeException;
import woo.Storefront;

/**
 * Register container.
 */
public class DoRegisterProductContainer extends Command<Storefront> {
  /** Input field. */
  private Input<String> _key;

  /** Input field. */
  private Input<Integer> _price;

  /** Input field. */
  private Input<Integer> _cValue;

  /** Input field. */
  private Input<String> _sID;

  /** Input field. */
  private Input<String> _sType;

  /** Input field. */
  private Input<String> _sLevel;

  /**
   * Constructor.
   * 
   * @param receiver
   */
  public DoRegisterProductContainer(Storefront receiver) {
    super(Label.REGISTER_CONTAINER, receiver);
    _key = _form.addStringInput(Message.requestProductKey());
    _price = _form.addIntegerInput(Message.requestPrice());
    _cValue = _form.addIntegerInput(Message.requestStockCriticalValue());
    _sID = _form.addStringInput(Message.requestSupplierKey());
    _sType = _form.addStringInput(Message.requestServiceType());
    _sLevel = _form.addStringInput(Message.requestServiceLevel());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    try {
      _form.parse();
      _receiver.registerContainer(_key.value(), _price.value(), _cValue.value(), 
                             _sID.value(), _sType.value(), _sLevel.value(), 0);
    } catch (DuplicateProductException e) {
      throw new DuplicateProductKeyException(e.getKey());
    } catch (UnknownSupplierException e) {
      throw new UnknownSupplierKeyException(e.getKey());
    } catch (UnknownServTypeException e) {
      throw new UnknownServiceTypeException(e.getType());
    } catch (UnknownServLevelException e) {
      throw new UnknownServiceLevelException(e.getLevel());
    }
  }
}
