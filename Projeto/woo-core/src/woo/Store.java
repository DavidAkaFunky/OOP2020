package woo;

import woo.exceptions.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class Store implements a store.
 */
public class Store implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192006L;
  private Map<Integer, Transaction> _transactions = new TreeMap<Integer, Transaction>();
  private Map<String, Client> _clients = new TreeMap<String, Client>();
  private Map<String, Product> _products = new TreeMap<String, Product>();
  private Map<String, Supplier> _suppliers = new TreeMap<String, Supplier>();

  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException, ImportFileException /* FIXME maybe other exceptions */ {
    /* BufferedReader reader = new BufferedReader(new FileReader(name));
    String line;
    while ((line = reader.readLine()) != null) {
      String[] fields = line.split("\\|");
      try {
        registerFromFields(fields);
      } catch (UnknownDataException e) {
        System.err.printf("WARNING: unknown data %s\n", e.getMessage());
        e.printStackTrace();
      } catch (PublicationExistsException e) {
        e.printStackTrace();
      } catch (UnknownAgentException e) {
        e.printStackTrace();
      } catch (ClientExistsException e) {
        e.printStackTrace();
      } catch (InvalidIdentifierException e) {
        e.printStackTrace();
      }
    }
    reader.close();*/
  }

  /* PARTE DOS PRODUTOS */

  public String showAllProducts() {
    /* IDEIA: Iterar por todos os produtos do TreeMap carregado a partir do file */
    return "";
  }

  public void registerBook(String id, String title, String author, String isbn, int price, int cValue, int sID) {
    return ;
  }

  public void registerBox(String id, int price, int cValue, int sID) {
    return ;
  }

  public void registerContainer(String id, int price, int cValue, int sID) {
    return ;
  }

  public void changeProductPrice(String id, int newPrice) {
    return ;
  }

  /* PARTE DOS CLIENTES */

  public String showAllClients() {
    /* IDEIA: Iterar por todos os clientes do TreeMap carregado a partir do file */
    String str = "";
    for (Map.Entry<String, Client> client : _clients.entrySet())
      str += client.getValue().toString() + "\n";
    return str;
  }

  public String showClient(String id) throws UnknownClientException {
    /* IDEIA: A partir do id, ir buscar ao TreeMap o cliente a partir do ID e chamar o toString */
    if (_clients.containsValue(id)){
      String str = _clients.get(id).toString() + _clients.get(id).showNotifications();
      _clients.get(id).clearNotifications();
      return str;
    }
    throw new UnknownClientException(id);
  }

  public void registerClient (String id, String name, String address) throws DuplicateClientException {
    /* IDEIA: Criar cliente e adicioná-lo à TreeMap de clientes */
    /* Lançar exceção se o id é repetido */
    if (_clients.containsValue(id))
      throw new DuplicateClientException(id);
    _clients.put(id, new Client(id, name, address));
  }

  public String toggleClientProductNotifications(String pid, String cid) throws UnknownClientException, UnknownProductException{
    if (_clients.containsValue(cid) && _products.containsValue(pid)){
      _clients.get(cid).setNotifiability(!_clients.get(cid).isNotifiable(pid), pid);
      if (_clients.get(cid).isNotifiable(pid))
        return "notificationsOn()";
      else
        return "notificationsOff()";
    }
    else{
      if (!_clients.containsValue(cid))
        throw new UnknownClientException(cid);
      if (!_products.containsValue(pid))
        throw new UnknownProductException(cid);
    }
    return "";
  }
  
  public String showClientTransactions(String id) throws UnknownClientException {
    if (_clients.containsValue(id))
      return _clients.get(id).showSales();
    throw new UnknownClientException(id);
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
    return ;
  }

  public void toggleTransactions(String id){
    /* IDEIA: Self-explanatory */
    return ;
  }
  
  public String showAllSupplierTransactions(String id){
    /* IDEIA: Ir ao TreeMap de encomendas e ver quais têm o id do fornecedor (usar o getter do fornecedor??)  */
    return "";
  }

  /* PARTE DAS TRANSAÇÕES */

  public void pay(int id){
    return ;
  }

  public void registerOrderTransaction(int supID, int pID, int amt){
    return ;
  }

  public void registerSaleTransaction(int cID, int limDate, int pID, int qty){
    return ;
  }

  public String showTransaction(int id){
    return "";
  }

  /* PARTE DAS PESQUISAS */

  public String showProductsPrice (int price){
    return "";
  }

  public String showClientBills (int cID){
    return "";
  }
}