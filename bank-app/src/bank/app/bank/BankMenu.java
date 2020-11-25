package bank.app.bank;

import bank.Bank;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Menu;

/**
 * This class represents the Menu for BankAccounts.
 */
public class BankMenu extends Menu {

  /**
   * @param bank
   */
  public BankMenu(Bank bank) {
    super(Labels.TITLE,
        new Command<?>[] { //
            new DoCreateHolder(bank), //
            new DoCreateAccount(bank), //
            new DoShowAccountHolders(bank), //
            new DoShowAccounts(bank), //
            new DoOpenHolderMenu(bank), //
            new DoOpenAccountMenu(bank) //
        });
  }
}
