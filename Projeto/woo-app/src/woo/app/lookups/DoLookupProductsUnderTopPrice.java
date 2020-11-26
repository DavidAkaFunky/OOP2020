package woo.app.lookups;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;
import woo.Product;
import woo.Storefront;                                                                                                                        //FIXME import other classes

/**
 * Lookup products cheaper than a given price.
 */
public class DoLookupProductsUnderTopPrice extends Command<Storefront> {

  private Input<Integer> _topPrice;

  public DoLookupProductsUnderTopPrice(Storefront storefront) {
    super(Label.PRODUCTS_UNDER_PRICE, storefront);
    _topPrice = _form.addIntegerInput(Message.requestPriceLimit());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    for (Product p : _receiver.lookupProductsUnderPrice(_topPrice.value())) {
      _display.addLine(p.toString());
    }
    _display.display();
  }
}
