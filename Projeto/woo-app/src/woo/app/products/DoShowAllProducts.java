package woo.app.products;

import woo.Product;
import woo.Storefront;
import java.util.Map.Entry;
import pt.tecnico.po.ui.Command;                                                                                                              
import pt.tecnico.po.ui.DialogException;                                                                                                      
import pt.tecnico.po.ui.Input;

/**
 * Show all products.
 */
public class DoShowAllProducts extends Command<Storefront> {

  public DoShowAllProducts(Storefront receiver) {
    super(Label.SHOW_ALL_PRODUCTS, receiver);
  }

  @Override
  public final void execute() throws DialogException {
    for (Entry<String, Product> p: _receiver.getProducts())
      _display.addLine(p.getValue().toString());
    _display.display();
  }

}
