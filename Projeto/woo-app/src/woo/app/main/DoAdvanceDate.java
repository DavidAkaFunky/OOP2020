package woo.app.main;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

/**
 * Advance current date.
 */
public class DoAdvanceDate extends Command<Storefront> {
  
  Input<Integer> _dayAmount;

  public DoAdvanceDate(Storefront receiver) {
    super(Label.ADVANCE_DATE, receiver);
    //FIXME init input fields
  }

  @Override
  public final void execute() throws DialogException {
    //FIXME implement command
  }
}
