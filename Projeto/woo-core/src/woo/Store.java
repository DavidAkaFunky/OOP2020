package woo;

import java.io.*;
import woo.exceptions.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class Store implements a store.
 */
public class Store implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192006L;
  private Map<Integer, Sale> _sales = new TreeMap<Integer, Sales>();
  private Map<Integer, Order> _orders = new TreeMap<Integer, Orders>();
  private Map<Integer, Client> _clients = new TreeMap<Integer, Client>();
  private Map<Integer, Product> _products = new TreeMap<Integer, Product>();

  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException /* FIXME maybe other exceptions */ {
    //FIXME implement method
  }

  /* PARTE DOS PRODUTOS */

  public String showAllProducts() {
    /* IDEIA: Iterar por todos os produtos do TreeMap carregado a partir do file */
    return "";
  }

  public void registerBook(int id, String title, String author, String isbn, int price, int cValue, int sId) {
    
  }

  public void registerBox(int id, int price, int cValue, int sId) {
    
  }

  public void registerContainer(int id, int price, int cValue, int sId) {
    
  }

  public void changeProductPrice(int id, int newPrice) {
    
  }

  /* PARTE DOS CLIENTES */

  public String showAllClients() {
    /* IDEIA: Iterar por todos os clientes do TreeMap carregado a partir do file */
    return "";
  }

  public String showClient(int id) {
    /* IDEIA: A partir do id, ir buscar ao TreeMap o cliente a partir do ID e chamar o toString */
    return "";
  }

  public void registerClient(int id, String name, String address) {
    /* IDEIA: Criar cliente e adicioná-lo à TreeMap de clientes */
    /* Lançar exceção se o id é repetido */
  }

  public void toggleClientProductNotifications(int id){
    /* IDEIA: Self-explanatory */
  }
  
  public String showAllClientAcquisitions(int id){
    /* IDEIA: Ir ao TreeMap de vendas e ver quais têm o id do cliente (usar o getter do cliente??)  */
  }

  /* PARTE DOS FORNECEDORES */

  public String showAllSuppliers() {
    /* IDEIA: Iterar por todos os fornecedores do TreeMap carregado a partir do file */
    return "";
  }

  public String showSupplier(int id) {
    /* IDEIA: A partir do id, ir buscar ao TreeMap o fornecedor a partir do ID e chamar o toString */
    return "";
  }

  public void registerSupplier(int id, String name, String address) {
    /* IDEIA: Criar fornecedor e adicioná-lo à TreeMap de fornecedores */
    /* Lançar exceção se o id é repetido */
  }

  public void toggleTransactions(int id){
    /* IDEIA: Self-explanatory */
  }
  
  public String showAllSupplierTransactions(int id){
    /* IDEIA: Ir ao TreeMap de encomendas e ver quais têm o id do fornecedor (usar o getter do fornecedor??)  */
  }

  /* PARTE DAS TRANSAÇÕES */

  public void pay(int id){

  }

  public void registerOrderTransaction(int supId, int pId, int amt){

  }

  public void registerSaleTransaction(int cId, int limDate, int pId, int qty){

  }

  public String showTransaction(int id){

  }
}