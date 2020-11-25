package bank.app.bankaccount;

import bank.BankAccount;
import bank.exceptions.BalanceTooLowException;
import bank.exceptions.InvalidWithdrawalException;
import bank.exceptions.NegativeAmountException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

/**
 * This class represents a withdrawal command.
 */
public class DoWithdraw extends Command<BankAccount> {
  /** Input field. */
  Input<Float> _amount;

	/**
	 * Constructor
	 * 
	 * @param account
	 */
	public DoWithdraw(BankAccount account) {
		super(Labels.WITHDRAW, account);
    _amount = _form.addFloatInput(Messages.requestAmount());
	}

  /** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		_form.parse();
		try {
      _receiver.withdraw(_amount.value());
    } catch (InvalidWithdrawalException e) {
      throw new InvalidWithdrawalUIException(e.getMessage());
    } catch (NegativeAmountException e) {
      throw new NegativeWithdrawalUIException(e.getAmount());
    } catch (BalanceTooLowException e) {
      throw new NoBalanceWithdrawalUIException(e.getBalance(), e.getAmount());
    }
	}
}
