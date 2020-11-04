package woo.app.products;

import pt.tecnico.po.ui.Command;                                                                                                              
import pt.tecnico.po.ui.DialogException;                                                                                                      
import pt.tecnico.po.ui.Input;                                                                                                                
import woo.Storefront;
import woo.app.exceptions.UnknownProductKeyException;
import woo.exceptions.UnknownProductException;

/**
 * Change product price.
 */
public class DoChangePrice extends Command<Storefront> {

  private Input<String> _key;
  private Input<Integer> _newPrice;
  
  public DoChangePrice(Storefront receiver) {
    super(Label.CHANGE_PRICE, receiver);
    _key = _form.addStringInput(Message.requestProductKey());
    _newPrice = _form.addIntegerInput(Message.requestPrice());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try {
      _receiver.changeProductPrice(_key.value(), _newPrice.value());
    } catch (UnknownProductException e) {
      throw new UnknownProductKeyException(_key.value());
    }
  }
}
