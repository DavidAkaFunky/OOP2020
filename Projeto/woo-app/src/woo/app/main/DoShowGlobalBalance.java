package woo.app.main;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

/**
 * Show global balance.
 */
public class DoShowGlobalBalance extends Command<Storefront> {

  public DoShowGlobalBalance(Storefront receiver) {
    super(Label.SHOW_BALANCE, receiver);
  }

  @Override
  public final void execute() {
    _display.addLine(Message.currentBalance(_receiver.getAvailableBalance(), _receiver.getAccountingBalance()));
    _display.display();
  }
}
