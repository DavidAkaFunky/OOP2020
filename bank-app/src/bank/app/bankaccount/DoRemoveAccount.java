package bank.app.bankaccount;

import bank.Bank;
import bank.BankAccount;
import bank.exceptions.InvalidRemovalException;
import pt.tecnico.po.ui.Command;

/**
 * This class represents the removal of a bank account. If successful, the
 * control should return to the previous menu.
 */
public class DoRemoveAccount extends Command<BankAccount> {
  /** The bank the account belongs to. */
  private Bank _bank;

  /**
   * Constructor.
   * 
   * @param account
   *          the target account.
   * @param bank
   *          the bank the account belongs to.
   */
  public DoRemoveAccount(BankAccount account, Bank bank) {
    super(true, Labels.REMOVE_ACCOUNT, account);
    _bank = bank;
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    try {
      _bank.removeAccount(_receiver);
    } catch (InvalidRemovalException ire) {
      _display.popup(bank.app.bank.Messages.errorRemovingAccount(ire.getMessage()));
      // throw new InvalidRemovalUIException(e.getAccount(), e.getAmount());
    }
  }
}
