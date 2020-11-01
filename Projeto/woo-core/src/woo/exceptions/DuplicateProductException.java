package woo.exceptions;

/** Exception thrown when a client key is duplicated. */
public class DuplicateProductException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201709021324L;

  /** Product key. */
  private String _key;

  /** @param key the duplicated key */
  public DuplicateProductException(String key) {
    _key = key;
  }

  public String getKey() {
    return _key;
  }

}
