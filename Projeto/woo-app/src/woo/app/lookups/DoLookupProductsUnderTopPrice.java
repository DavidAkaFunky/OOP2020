package woo.app.lookups;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.Product;
import woo.Storefront;

/**
 * Lookup products cheaper than a given price.
 */
public class DoLookupProductsUnderTopPrice extends Command<Storefront> {
  /** Input field. */
  private Input<Integer> _topPrice;

  /**
   * Constructor.
   * 
   * @param storefront
   */
  public DoLookupProductsUnderTopPrice(Storefront storefront) {
    super(Label.PRODUCTS_UNDER_PRICE, storefront);
    _topPrice = _form.addIntegerInput(Message.requestPriceLimit());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public void execute() throws DialogException {
    _form.parse();
    for (Product p : _receiver.lookupProductsUnderPrice(_topPrice.value())) {
      _display.addLine(p.toString());
    }
    _display.display();
  }
}
