package bank.app.bankaccount;

import bank.BankAccount;
import bank.exceptions.InvalidDepositException;
import bank.exceptions.NegativeAmountException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

/**
 * Class Deposit represents commands for making deposits.
 */
public class DoDeposit extends Command<BankAccount> {
  /** Input field. */
  Input<Float> _amount;

	/**
	 * Constructor.
	 * 
	 * @param account the target account.
	 */
	public DoDeposit(BankAccount account) {
		super(Labels.DEPOSIT, account);
    _amount = _form.addFloatInput(Messages.requestAmount());
	}

  /** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		try {
	    _form.parse();
      _receiver.deposit(_amount.value());
    } catch (InvalidDepositException e) {
      throw new InvalidDepositUIException();
    } catch (NegativeAmountException e) {
      throw new NegativeDepositUIException(e.getAmount());
    }
	}
}
