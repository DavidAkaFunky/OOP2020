package woo.app.suppliers;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exceptions.DuplicateSupplierKeyException;
import woo.exceptions.DuplicateSupplierException;
import woo.Storefront;

/**
 * Register supplier.
 */
public class DoRegisterSupplier extends Command<Storefront> {
  /** Input field. */
  private Input<String> _key;

  /** Input field. */
  private Input<String> _name;

  /** Input field. */
  private Input<String> _address;

  /**
   * Constructor.
   * 
   * @param receiver
   */
  public DoRegisterSupplier(Storefront receiver) {
    super(Label.REGISTER_SUPPLIER, receiver);
    _key = _form.addStringInput(Message.requestSupplierKey());
    _name = _form.addStringInput(Message.requestSupplierName());
    _address = _form.addStringInput(Message.requestSupplierAddress());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public void execute() throws DialogException {
    try {
      _form.parse();
      _receiver.registerSupplier(_key.value(), _name.value(), _address.value());
    } catch (DuplicateSupplierException e) {
      throw new DuplicateSupplierKeyException(e.getKey());
    }
  }

}
