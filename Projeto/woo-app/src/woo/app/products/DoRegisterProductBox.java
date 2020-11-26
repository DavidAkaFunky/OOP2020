package woo.app.products;

import pt.tecnico.po.ui.Command;  
import woo.exceptions.DuplicateProductException;
import woo.exceptions.UnknownServTypeException;
import woo.app.exceptions.DuplicateProductKeyException;
import woo.app.exceptions.UnknownServiceTypeException;
import woo.exceptions.UnknownSupplierException;
import woo.app.exceptions.UnknownSupplierKeyException;                                                                                                            import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

/**
 * Register box.
 */
public class DoRegisterProductBox extends Command<Storefront> {

  private Input<String> _key;
  private Input<Integer> _price;
  private Input<Integer> _cValue;
  private Input<String> _sID;
  private Input<String> _sType;

  public DoRegisterProductBox(Storefront receiver) {
    super(Label.REGISTER_BOX, receiver);
    _key = _form.addStringInput(Message.requestProductKey());
    _price = _form.addIntegerInput(Message.requestPrice());
    _cValue = _form.addIntegerInput(Message.requestStockCriticalValue());
    _sID = _form.addStringInput(Message.requestSupplierKey());
    _sType = _form.addStringInput(Message.requestServiceType());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try {
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
