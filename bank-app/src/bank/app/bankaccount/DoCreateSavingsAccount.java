package bank.app.bankaccount;

import bank.BankAccount;
import bank.exceptions.BalanceTooLowException;
import bank.exceptions.InvalidWithdrawalException;
import bank.exceptions.NegativeAmountException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

/**
 * Class for creating a savings account.
 */
public class DoCreateSavingsAccount extends Command<BankAccount> {
  /** Input field. */
  Input<Float> _amount;

  /** Input field. */
  Input<Float> _rate;

  /** Input field. */
  Input<Integer> _time;

  /**
   * Constructor.
   * 
   * @param account
   *          the target bank account.
   */
  public DoCreateSavingsAccount(BankAccount account) {
    super(Labels.CREATE_SAVINGS_ACCOUNT, account);
    _amount = _form.addFloatInput(Messages.requestAmount());
    _rate = _form.addFloatInput(Messages.requestRate());
    _time = _form.addIntegerInput(Messages.requestTime());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    try {
      _form.parse();
      _receiver.withdraw(_amount.value());
      _receiver.addSavingsAccount(_amount.value(), _rate.value(), _time.value());
    } catch (InvalidWithdrawalException e) {
      throw new InvalidWithdrawalUIException(Messages.invalidWithdrawal());
    } catch (NegativeAmountException e) {
      throw new NegativeDepositUIException(e.getAmount());
    } catch (BalanceTooLowException e) {
      throw new NoBalanceWithdrawalUIException(e.getBalance(), e.getAmount());
    }
  }
}
