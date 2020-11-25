package woo.app.products;

import pt.tecnico.po.ui.Command;      

import pt.tecnico.po.ui.DialogException;
import woo.exceptions.DuplicateProductException;
import woo.app.exceptions.DuplicateProductKeyException;
import woo.exceptions.UnknownSupplierException;
import woo.app.exceptions.UnknownSupplierKeyException;   
import woo.exceptions.UnknownServLevelException;
import woo.exceptions.UnknownServTypeException;
import woo.app.exceptions.UnknownServiceLevelException;
import woo.app.exceptions.UnknownServiceTypeException;                                                                                                import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

/**
 * Register container.
 */
public class DoRegisterProductContainer extends Command<Storefront> {

  private Input<String> _key;
  private Input<Integer> _price;
  private Input<Integer> _cValue;
  private Input<String> _sID;
  private Input<String> _sType;
  private Input<String> _sLevel;

  public DoRegisterProductContainer(Storefront receiver) {
    super(Label.REGISTER_CONTAINER, receiver);
    _key = _form.addStringInput(Message.requestProductKey());
    _price = _form.addIntegerInput(Message.requestPrice());
    _cValue = _form.addIntegerInput(Message.requestStockCriticalValue());
    _sID = _form.addStringInput(Message.requestSupplierKey());
    _sType = _form.addStringInput(Message.requestServiceType());
    _sLevel = _form.addStringInput(Message.requestServiceLevel());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try {
      _receiver.registerContainer(_key.value(), _price.value(), _cValue.value(), 
                             _sID.value(), _sType.value(), _sLevel.value(), 1);
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
