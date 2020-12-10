package woo.exceptions;

public class IncorrectSupplierException extends Exception {
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
  public IncorrectSupplierException(String sKey, String pKey) {
    _sKey = sKey;
    _pKey = pKey;
  }

  /**
   * @return the incorrent supplier key.
   */
  public String getSupplierKey() {
    return _sKey;
  }

  /**
   * @return the product key whose is supplier isn't given supplier key.
   */
  public String getProductKey() {
    return _pKey;
  }
  
}