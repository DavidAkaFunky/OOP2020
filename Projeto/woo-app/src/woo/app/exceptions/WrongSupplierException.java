package woo.app.exceptions;

import pt.tecnico.po.ui.DialogException;

/** Exception for reporting wrong supplier/product associations. */
public class WrongSupplierException extends DialogException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009200054L;

  /** Supplier key. */
  private String _sKey;

  /** Product key. */
  private String _pKey;

  /** 
   * @param sKey supplier key.
   * @param pKey product key. 
   */
  public WrongSupplierException(String sKey, String pKey) {
    _sKey = sKey;
    _pKey = pKey;
  }

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
  @Override
  public String getMessage() {
    return Message.wrongSupplier(_sKey, _pKey);
  }

}
