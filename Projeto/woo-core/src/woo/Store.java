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
  private Map<String, Client> _clients = new TreeMap<String, Client>();
  private Map<String, Product> _products = new TreeMap<String, Product>();
  private Map<String, Supplier> _suppliers = new TreeMap<String, Supplier>();

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

  public void registerBook(String id, String title, String author, String isbn, int price, int cValue, int sID) {
    
  }

  public void registerBox(String id, int price, int cValue, int sID) {
    
  }

  public void registerContainer(String id, int price, int cValue, int sID) {
    
  }

  public void changeProductPrice(String id, int newPrice) {
    
  }

  /* PARTE DOS CLIENTES */

  public String showAllClients() {
    /* IDEIA: Iterar por todos os clientes do TreeMap carregado a partir do file */
    return "";
  }

  public String showClient(String id) {
    /* IDEIA: A partir do id, ir buscar ao TreeMap o cliente a partir do ID e chamar o toString */
    return "";
  }

  public void registerClient(String id, String name, String address) {
    /* IDEIA: Criar cliente e adicioná-lo à TreeMap de clientes */
    /* Lançar exceção se o id é repetido */
  }

  public void toggleClientProductNotifications(String id){
    /* IDEIA: Self-explanatory */
  }
  
  public String showAllClientAcquisitions(String id){
    /* IDEIA: Ir ao TreeMap de vendas e ver quais têm o id do cliente (usar o getter do cliente??)  */
  }

  /* PARTE DOS FORNECEDORES */

  public String showAllSuppliers() {
    /* IDEIA: Iterar por todos os fornecedores do TreeMap carregado a partir do file */
    return "";
  }

  public String showSupplier(String id) {
    /* IDEIA: A partir do id, ir buscar ao TreeMap o fornecedor a partir do ID e chamar o toString */
    return "";
  }

  public void registerSupplier(String id, String name, String address) {
    /* IDEIA: Criar fornecedor e adicioná-lo à TreeMap de fornecedores */
    /* Lançar exceção se o id é repetido */
  }

  public void toggleTransactions(String id){
    /* IDEIA: Self-explanatory */
  }
  
  public String showAllSupplierTransactions(String id){
    /* IDEIA: Ir ao TreeMap de encomendas e ver quais têm o id do fornecedor (usar o getter do fornecedor??)  */
  }

  /* PARTE DAS TRANSAÇÕES */

  public void pay(int id){

  }

  public void registerOrderTransaction(int supID, int pID, int amt){

  }

  public void registerSaleTransaction(int cID, int limDate, int pID, int qty){

  }

  public String showTransaction(int id){

  }

  /* PARTE DAS PESQUISAS */

  public String showProductsPrice (int price){

  }

  public String showClientBills (int cID){
    
  }
}