package bank.app;

import static pt.tecnico.po.ui.Dialog.IO;

import java.io.IOException;

import bank.Bank;
import bank.BankIO;
import bank.app.bank.BankMenu;

/**
 * Bank application.
 */
public class App {
  /**
   * @param args
   */
  @SuppressWarnings("nls")
  public static void main(String[] args) {
    Bank bank = null;
    String file = System.getProperty("Save");

    /* Create a Bank instance */
    if (args.length == 1) {
      bank = new Bank(args[0]);
    } else {
      if (file != null)
        try {
          bank = BankIO.load(file);
        } catch (Exception e) {
          System.out.println("Erro : " + file + " : " + e);
        }
      if (bank == null)
        bank = new Bank("Banco");
    }

    /* Read an Import file, if any */
    String filename = System.getProperty("Import");
    if (filename != null)
      BankIO.addFromInputFile(bank, filename);

    /* Start the user interface with the Bank instance */
    IO.setTitle(bank.getName());
    BankMenu m = new BankMenu(bank);
    m.open();

    /* On end, save file and close IO */
    try {
      if (file != null)
        BankIO.save(bank, file);
    } catch (IOException e) {
      System.out.println("Erro : " + file + " : " + e);
    }

    IO.close();
  }

}
