package bank.app.bank;

import pt.tecnico.po.ui.DialogException;

/**
 * User exception for presenting bad dates.
 */
@SuppressWarnings("nls")
public class InvalidDateException extends DialogException {

  /** Serial number. */
  private static final long serialVersionUID = 4928801929604037903L;

  /** The invalid date. */
  private int _date;

  /**
   * @param date
   */
  public InvalidDateException(int date) {
    _date = date;
  }

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
  @Override
  public String getMessage() {
    return "Data inválida: " + _date;
  }

}
