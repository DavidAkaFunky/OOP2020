package bank.app.bankaccount;

import bank.Bank;
import bank.BankAccount;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Menu;

/**
 * This class represents the Menu for BankAccounts.
 */
public class BankAccountMenu extends Menu {

  /**
   * Constructor for class BankAccountMenu
   * 
   * @param ba
   * @param b
   *          the bank this account belongs to.
   */
  public BankAccountMenu(BankAccount ba, Bank b) {
    super(Labels.TITLE,
        new Command<?>[] { //
            new DoDeposit(ba), //
            new DoWithdraw(ba), //
            new DoShowBalance(ba, false), //
            new DoShowBalance(ba, true), //
            new DoShowAccountHolders(ba), //
            new DoCreateSavingsAccount(ba), //
            new DoShowAllSavingsAccounts(ba), //
            new DoShowSpecificSavingsAccount(ba), //
            new DoRemoveAccount(ba, b) //
        });
  }
}
