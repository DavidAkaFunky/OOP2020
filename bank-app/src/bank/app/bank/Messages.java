package bank.app.bank;

/**
 * Messages.
 */
@SuppressWarnings("nls")
public class Messages {

  /**
   * @return string prompting for an account holder's id.
   */
  public static String requestHolderId() {
    return "Identificador do titular: ";
  }

  /**
   * @param id
   *          the missing holder's id.
   * @return string communication an error.
   */
  public static String noSuchHolder(int id) {
    return "Não existe nenhum titular com o número " + id + ".";
  }

  /**
   * @param error
   * @return the string describing an exception concerning an unknown holder id.
   */
  public static final String errorShowingHolder(String error) {
    return "Mostrar Titular: " + error;
  }

  /**
   * @return prompt for name.
   */
  public static String requestHolderName() {
    return "Nome do titular: ";
  }

  /**
   * @param id
   * @return string describing holder creation success.
   */
  public static String createdHolder(int id) {
    return "Titular criado: " + id;
  }

  /**
   * @param name
   * @param id
   * @return string describing holder creation success.
   */
  public static String createdHolder(String name, int id) {
    return "Titular '" + name + "', id '" + id + "' criado";
  }

  /**
   * @param error
   * @return the string describing an exception concerning an unknown holder id.
   */
  public static final String errorCreatingHolder(String error) {
    return "Erro na criação de titular: " + error;
  }

  /**
   * @return prompt for initial account balance.
   */
  public static final String requestInitialBalance() {
    return "Saldo inicial? ";
  }

  /**
   * @param accountId
   *          the new account
   * @return account creation message
   */
  public static final String accountCreated(int accountId) {
    return "Conta criada: " + accountId;
  }

  /**
   * @param error
   *          error description
   * @return error string
   */
  public static final String errorCreatingAccount(String error) {
    return "Erro na criação de conta: " + error;
  }

  /**
   * @return prompt for account id.
   */
  public static final String requestAccountId() {
    return "Número de conta? ";
  }

  /**
   * @param id
   *          the account's id
   * @return error string
   */
  public static final String errorSelectingAccount(int id) {
    return "Não existe uma conta com o número " + id;
  }

  /**
   * @param error
   *          error description
   * @return error string
   */
  public static final String errorShowingAccount(String error) {
    return "Mostrar Conta: " + error;
  }

  /**
   * @param error
   *          error description (the balance as a string)
   * @return error string
   */
  public static final String errorRemovingAccount(String error) {
    return "Não é possível remover a conta. Saldo é " + error;
  }

  /**
   * @param account
   * @param amount
   * @return error string
   */
  public static final String errorRemovingAccount(int account, double amount) {
    return "Não é possível remover a conta " + account + ": saldo é " + amount;
  }
  
  /**
   * @return Holder key.
   */
  public static String holderKey() {
    return "HOLDER";
  }

  /**
   * @return Account key.
   */
  public static String accountKey() {
    return "ACCOUNT";
  }
}
