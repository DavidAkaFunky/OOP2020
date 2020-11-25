/**
 * 
 */
package bank.app.bankaccount;

/**
 * 
 */
@SuppressWarnings("nls")
public class Messages {
  /**
   * @param balance
   * @return string describing the current balance of the accounts
   */
  public static final String showCurrentAccountBalance(double balance) {
    return "Saldo corrente: " + balance;
  }

  /**
   * @param balance
   * @return string describing the total balance of the accounts
   */
  public static final String showTotalBalance(double balance) {
    return "Saldo total: " + balance;
  }

  /**
   * @return title for menu entry for getting the current account balance.
   */
  public static final String titleCurrentAccountBalance() {
    return "Saldo Corrente";
  }

  /**
   * @return title for menu entry for getting the total balance.
   */
  public static final String titleTotalBalance() {
    return "Saldo Total";
  }

  /**
   * @return error string
   */
  public static final String errorShowingSavingsAccounts() {
    return "A conta não tem contas poupança";
  }

  /**
   * @return prompt for a savings account id.
   */
  public static final String requestSavingsAccountId() {
    return "Número da conta poupança: ";
  }

  /**
   * @param error
   * @return error string
   */
  public static final String errorExecutingOperation(String error) {
    return "Erro ao executar operação " + error;
  }

  /**
   * @return error string
   */
  public static final String errorShowingSavingsAccount() {
    return "Não existe nenhuma conta poupança com o identificador indicado";
  }

  /**
   * @return prompt for amount
   */
  public static final String requestAmount() {
    return "Valor? ";
  }

  /**
   * @return interest rate
   */
  public static final String requestRate() {
    return "Taxa? ";
  }

  /**
   * @return request time period for savings account
   */
  public static final String requestTime() {
    return "Prazo? ";
  }

  /**
   * @return invalid deposit message
   */
  public static final String invalidDeposit() {
    return "Depósito inválido";
  }

  /**
   * @param amount
   *          invalid negative amount
   * @return invalid deposit message
   */
  public static String invalidNegativeAmountDeposit(double amount) {
    return "Depósito inválido: tentativa de depositar " + amount;
  }

  /**
   * @return invalid withdrawal message
   */
  public static String invalidWithdrawal() {
    return "Levantamento inválido";
  }

  /**
   * @param amount
   *          invalid negative amount
   * @return invalid withdrawal message
   */
  public static String invalidNegativeAmountWithdrawal(double amount) {
    return "Levantamento inválido: tentativa de levantar " + amount;
  }

  /**
   * @param balance
   * @param amount
   * @return invalid withdrawal message
   */
  public static String notEnoughBalance(double balance, double amount) {
    return "Levantamento inválido: tentativa de levantar " + amount + " (saldo " + balance + ")";
  }

}
