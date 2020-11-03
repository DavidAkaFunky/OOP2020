package woo;

import woo.exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.Set;
import java.util.ArrayList;

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
  private int _date = 0;

  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws ImportFileException {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(txtfile));
      Store store;
      String line;
      while ((line = reader.readLine()) != null) {
        Pattern pSupplier = Pattern.compile("^(SUPPLIER)");
        Pattern pClient = Pattern.compile("^(CLIENT)");
        Pattern pBox = Pattern.compile("^(BOX)");
        Pattern pContainer = Pattern.compile("^(CONTAINER)");
        Pattern pBook = Pattern.compile("^(BOOK)");
        String[] input = line.split("\\|");
        if (pSupplier.matcher(input[0]).matches()) {
          registerSupplier(input[1], input[2], input[3]);
        }
        else if (pClient.matcher(input[0]).matches()) {
          registerClient(input[1], input[2], input[3]);
        }
        else if (pBox.matcher(input[0]).matches()) {
          int price = Integer.parseInt(input[4]);
          int cValue = Integer.parseInt(input[5]);
          int amount = Integer.parseInt(input[6]);
          registerBox(input[1], price, cValue, input[3], input[2], amount);
        }
        else if (pContainer.matcher(input[0]).matches()) {
          int price = Integer.parseInt(input[5]);
          int cValue = Integer.parseInt(input[6]);
          int amount = Integer.parseInt(input[7]);
          registerContainer(input[1], price, cValue, input[4], input[2], input[3], amount);
        }
        else if (pBook.matcher(input[0]).matches()) {
          int price = Integer.parseInt(input[6]);
          int cValue = Integer.parseInt(input[7]);
          int amount = Integer.parseInt(input[8]);
          registerBook(input[1], input[2], input[3], input[4], price, cValue, input[5], amount);
        }
      }
    } catch (IOException e) {
      throw new ImportFileException();
    } catch (DuplicateSupplierException e) {
      throw new ImportFileException();
    } catch (DuplicateClientException e) {
      throw new ImportFileException();
    } catch (DuplicateProductException e) {
      throw new ImportFileException();
    } catch (UnknownSupplierException e) {
      throw new ImportFileException();
    } catch (UnknownServTypeException e) {
      throw new ImportFileException();
    } catch (UnknownServLevelException e) {
      throw new ImportFileException();
    }
   }

  /* PARTE DOS PRODUTOS */

  public Set<Map.Entry<String,Product>> getProducts() {
    /* IDEIA: Iterar por todos os produtos do TreeMap carregado a partir do file */
    return _products.entrySet();
  }

  public void registerBook(String id, String title, String author, String isbn, int price, int cValue, String sID, int amount) throws DuplicateProductException, UnknownSupplierException {
    if (_products.containsKey(id)) {
      throw new DuplicateProductException(id);
    }
    else if (!_suppliers.containsKey(sID)) {
      throw new UnknownSupplierException(sID);
    }
    else {
      Supplier supplier = _suppliers.get(sID);
      _products.put(id, new Book(supplier, id, price, cValue, title, author, isbn, amount));
    }
  }

  public void registerBox(String id, int price, int cValue, String sID, String serviceType, int amount) throws DuplicateProductException, UnknownSupplierException, UnknownServTypeException {
    if (_products.containsKey(id)) {
      throw new DuplicateProductException(id);
    }
    else if (!_suppliers.containsKey(sID)) {
      throw new UnknownSupplierException(sID);
    }
    else {
      Supplier supplier = _suppliers.get(sID);
      _products.put(id, new Box(supplier, id, price, cValue, serviceType, amount));
    }
  }

  public void registerContainer(String id, int price, int cValue, String sID, String serviceType, String serviceLevel, int amount) throws DuplicateProductException, UnknownSupplierException, UnknownServTypeException, UnknownServLevelException{
    if (_products.containsKey(id)) {
      throw new DuplicateProductException(id);
    }
    else if (!_suppliers.containsKey(sID)) {
      throw new UnknownSupplierException(sID);
    }
    else {
      Supplier supplier = _suppliers.get(sID);
      _products.put(id, new Container(supplier, id, price, cValue, amount, serviceType, serviceLevel));
    }
  }

  public void changeProductPrice(String id, int newPrice) throws UnknownProductException {
    if (_products.containsKey(id)) {
      _products.get(id).setPrice(newPrice);
    } else {
      throw new UnknownProductException(id);
    }
  }

  /* PARTE DOS CLIENTES */

  public Set<Map.Entry<String,Client>> getClients() {
    /* IDEIA: Iterar por todos os clientes do TreeMap carregado a partir do file */
    return _clients.entrySet();
  }

  public Client getClient(String id) throws UnknownClientException {
    /* IDEIA: A partir do id, ir buscar ao TreeMap o cliente a partir do ID e chamar o toString */
    if (_clients.containsKey(id)) {
      return _clients.get(id);
    }
    throw new UnknownClientException(id);
  }

  public void registerClient (String id, String name, String address) throws DuplicateClientException {
    /* IDEIA: Criar cliente e adicioná-lo à TreeMap de clientes */
    /* Lançar exceção se o id é repetido */
    if (_clients.containsKey(id)) {
      throw new DuplicateClientException(id);
    }
    _clients.put(id, new Client(id, name, address));
  }

  public String toggleClientProductNotifications(String pid, String cid) throws UnknownClientException, UnknownProductException{
    if (_clients.containsKey(cid) && _products.containsKey(pid)){
      _clients.get(cid).setNotifiability(!_clients.get(cid).isNotifiable(pid), pid);
      //if (_clients.get(cid).isNotifiable(pid))
        //return Message.notificationsOn();
      //else
        //return Message.notificationsOff();
    }
    else{
      if (!_clients.containsKey(cid))
        throw new UnknownClientException(cid);
      if (!_products.containsKey(pid))
        throw new UnknownProductException(cid);
    }
    return "";
  }
  
  public ArrayList<Sale> getClientTransactions(String id) throws UnknownClientException {
    if (_clients.containsKey(id))
      return _clients.get(id).getSales();
    throw new UnknownClientException(id);
  }

  /* PARTE DOS FORNECEDORES */

  public Set<Map.Entry<String,Supplier>> getSuppliers() {
    /* IDEIA: Iterar por todos os produtos do TreeMap carregado a partir do file */
    return _suppliers.entrySet();
  }

  public void registerSupplier(String id, String name, String address) throws DuplicateSupplierException {
    if (_suppliers.containsKey(id)) {
      throw new DuplicateSupplierException(id);
    }
    _suppliers.put(id, new Supplier(id, name, address));
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

  public int getDate() {
    return _date;
  }

  public void advanceDate(int days) throws InvalidDaysException {
    if (days > 0) {
      _date += days;
    } else {
      throw new InvalidDaysException(days);
    }
  }
}