package woo.app.products;

import pt.tecnico.po.ui.Command;  
import pt.tecnico.po.ui.DialogException;
import java.util.Map.Entry;   
import woo.Product;
import woo.Storefront;

/**
 * Show all products.
 */
public class DoShowAllProducts extends Command<Storefront> {
  /**
   * Constructor.
   * 
   * @param receiver
   */
  public DoShowAllProducts(Storefront receiver) {
    super(Label.SHOW_ALL_PRODUCTS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    for (Product p: _receiver.getProducts()) {
      _display.addLine(p.toString());
    }
    _display.display();
  }

}
