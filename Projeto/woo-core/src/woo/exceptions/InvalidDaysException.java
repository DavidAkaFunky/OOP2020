package woo.exceptions;

/**
 * Class for representing a invalid date to advance.
 */
public class InvalidDaysException extends Exception {
/** Serial number for serialization. */
  private static final long serialVersionUID = 202009192335L;

  /** Bad date. */
  private int _date;

  /** @param date bad date to report. */
  public InvalidDaysException(int date) {
    _date = date;
  }

  public int getDate() {
      return _date;
  }
}