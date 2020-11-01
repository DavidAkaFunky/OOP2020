package woo.exceptions;

/** Exception for unknown supplier keys. */
public class UnknownSupplierKeyException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009200054L;

  /** Unknown key. */
  private String _key;

  /** @param key Unknown key to report. */
  public UnknownSupplierKeyException(String id) {
    _key = id;
  }

  public String getKey() {
    return _key;
  }

}
