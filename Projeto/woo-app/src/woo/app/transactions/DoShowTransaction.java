package woo.app.transactions;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes
import woo.Transaction;
import woo.app.exceptions.UnknownTransactionKeyException;
import woo.exceptions.UnknownTransactionException;

/**
 * Show specific transaction.
 */
public class DoShowTransaction extends Command<Storefront> {

  private Input<Integer> _id;

  public DoShowTransaction(Storefront receiver) {
    super(Label.SHOW_TRANSACTION, receiver);
    _id = _form.addIntegerInput(Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try {
      Transaction transaction = _receiver.getTransaction(_id.value());
      _display.addLine(transaction.toString());
      _display.display();
    } catch(UnknownTransactionException e) {
      throw new UnknownTransactionKeyException(e.getKey());
    }
  }

}
