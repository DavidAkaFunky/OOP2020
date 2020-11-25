package bank.app.holder;

import bank.Holder;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Menu;

/**
 * This class represents the Menu for BankAccounts.
 */
public class BankHolderMenu extends Menu {

  /**
   * Constructor for class BankAccountMenu
   * 
   * @param h
   */
  public BankHolderMenu(Holder h) {
    super(Labels.TITLE, new Command<?>[] { //
//        new Command<Holder>(MenuEntries.ADD_TO_ACCOUNT, h) {
//          public final void execute() {
//            Form f = new Form(title());
//            InputInteger n = new InputInteger(f, Messages.requestAccount());
//            f.parse();
//            BankAccount acc = entity().getBank().getAccount(n.value());
//            if (acc != null)
//              acc.addHolder(entity());
//            else
//              _display.popup(bank.messages.bank.Messages.errorSelectingAccount(n.value()));
//          }
//        }, //
        new DoChangeName(h) // TODO (alunos)
    });
  }
}
