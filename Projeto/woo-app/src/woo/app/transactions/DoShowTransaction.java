package woo.app.transactions;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exceptions.UnknownTransactionKeyException;
import woo.exceptions.UnknownTransactionException;
import woo.Transaction;
import woo.Storefront;

/**
 * Show specific transaction.
 */
public class DoShowTransaction extends Command<Storefront> {
  /** Input field. */
  private Input<Integer> _id;

  /**
   * Constructor.
   * 
   * @param receiver
   */
  public DoShowTransaction(Storefront receiver) {
    super(Label.SHOW_TRANSACTION, receiver);
    _id = _form.addIntegerInput(Message.requestTransactionKey());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
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
