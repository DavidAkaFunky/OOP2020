package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.Storefront;

/**
 * Show current date.
 */
public class DoDisplayDate extends Command<Storefront> {
  /**
   * Constructor.
   * 
   * @param receiver
   */
  public DoDisplayDate(Storefront receiver) {
    super(Label.SHOW_DATE, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _display.popup(Message.currentDate(_receiver.getDate()));
  }
}
