package woo.app.transactions;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes
import woo.exceptions.UnknownTransactionException;
import woo.app.exceptions.UnknownTransactionKeyException;

/**
 * Pay transaction (sale).
 */
public class DoPay extends Command<Storefront> {

  private Input<Integer> _transactionID;
  
  public DoPay(Storefront storefront) {
    super(Label.PAY, storefront);
    _transactionID = _form.addIntegerInput(Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try {
      _receiver.pay(_transactionID.value());
    } catch(UnknownTransactionException e) {
      throw new UnknownTransactionKeyException(e.getKey());
    }
   }

}
