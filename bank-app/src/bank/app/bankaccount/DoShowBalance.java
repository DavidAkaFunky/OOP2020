package bank.app.bankaccount;

import bank.BankAccount;
import pt.tecnico.po.ui.Command;

/**
 * Class representing a command for showing the balance of an account.
 */
public class DoShowBalance extends Command<BankAccount> {
  /** Should present the total balance? */
  private boolean _totalBalance = false;

  /**
   * Constructor.
   * 
   * @param account
   *          the target bank account.
   * @param totalBalance
   *          if true, show the total balance; otherwise, show only the current
   *          account's balance.
   */
  public DoShowBalance(BankAccount account, boolean totalBalance) {
    super((totalBalance ? Messages.titleTotalBalance() : Messages.titleCurrentAccountBalance()),
        account);
    _totalBalance = totalBalance;
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _display.popup(_totalBalance ? Messages.showTotalBalance(_receiver.getBalance())
        : Messages.showCurrentAccountBalance(_receiver.getCurrentAccountBalance()));
  }
}
