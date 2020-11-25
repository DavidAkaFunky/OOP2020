package bank.app.savingsaccount;

import bank.SavingsAccount;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Menu;

/**
 * This class represents the Menu for BankAccounts.
 */
public class BankSavingsAccountMenu extends Menu {

  /**
   * Constructor for class BankSavingsAccountMenu
   *
   * @param sa
   *          the bank this account belongs to.
   */
  public BankSavingsAccountMenu(SavingsAccount sa) {
    super(Labels.TITLE, new Command<?>[] { //
        new DoShowBalance(sa), //
        new DoCloseAccount(sa) });
  }
}
