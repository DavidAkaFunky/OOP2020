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
  private int _transactionID = 0;


  /**
   * @param txtfile a filename on which data will be imported onto the store
   * @throws IOException
   * @throws BadEntryException if there's an unknown import file entry
   */
  void importFile(String txtfile) throws IOException, BadEntryException, DuplicateSupplierException, UnknownSupplierException, 
                                         DuplicateClientException, UnknownServTypeException, DuplicateProductException, UnknownServLevelException {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(txtfile));
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
        } else {
          throw new BadEntryException(input[0]);
        }
      }
      reader.close();
    } catch (IOException e) {
      throw new IOException();
    }
   }

  /**
   * @return a Set with all Products available in store
   */
  public Set<Map.Entry<String,Product>> getProducts() {
    return _products.entrySet();
  }


  /**
   * @param id represents Book's ID
   * @param title represents Book's title
   * @param author represents Book's author
   * @param isbn represents Book's ISBN
   * @param price represents Book's price value
   * @param cValue represents Book's critical value
   * @param sID represents Book's supplier ID
   * @param amount represents Book's stock amount
   * @throws DuplicateProductException if Product ID already exists
   * @throws UnknownSupplierException if Supplier ID doesn't exist
   */
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

  /**
   * @param id represents Box's ID
   * @param price represents Box's price value
   * @param cValue represents Box's crical value
   * @param sID represents Box's supplier ID
   * @param serviceType represents Box's service type
   * @param amount represents Box's stock amount
   * @throws DuplicateProductException if Product ID already exists
   * @throws UnknownSupplierException if Supplier ID doesn't exist
   * @throws UnknownServTypeException if Service Type doesn't exist
   */
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


  /**
   * @param id represents Container's ID
   * @param price represents Container's price
   * @param cValue represents Container's critical value
   * @param sID represents Supplier's ID
   * @param serviceType represents Container's service type
   * @param serviceLevel represents Container's service level
   * @param amount represents Container's stock amount
   * @throws DuplicateProductException if Product ID doesn't exist
   * @throws UnknownSupplierException if Supplier ID doesn't exist
   * @throws UnknownServTypeException if Service Type doesn't exist
   * @throws UnknownServLevelException if Service Level doesn't exist
   */
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


  /**
   * @param id represents Product's ID
   * @param newPrice represents Product's new price
   * @throws UnknownProductException if Product ID doesn't exist
   */
  public void changeProductPrice(String id, int newPrice) throws UnknownProductException {
    if (_products.containsKey(id)) {
      _products.get(id).setPrice(newPrice);
    } else {
      throw new UnknownProductException(id);
    }
  }


  /**
   * @return a Set will all Clients available in Store
   */
  public Set<Map.Entry<String,Client>> getClients() {
    return _clients.entrySet();
  }


  /**
   * @param id represents Client's ID
   * @return an instance of Client class given its ID
   * @throws UnknownClientException if Client ID doesn't exist
   */
  public Client getClient(String id) throws UnknownClientException {
    if (_clients.containsKey(id)) {
      return _clients.get(id);
    }
    throw new UnknownClientException(id);
  }

  /**
   * Registers a client.
   * @param id represents Client's ID
   * @param name represents Client's name
   * @param address represent Client's address
   * @throws DuplicateClientException if Client ID already exists
   */
  public void registerClient(String id, String name, String address) throws DuplicateClientException {
    if (_clients.containsKey(id)) {
      throw new DuplicateClientException(id);
    }
    _clients.put(id, new Client(id, name, address));
  }


  /**
   * Toggles a Product notification given a Client.
   * @param pid represents Product's ID
   * @param cid represents Client's ID
   * @throws UnknownClientException if Client ID doesn't exist
   * @throws UnknownProductException if Product ID doesn't exist
   */
  public void toggleClientProductNotifications(String pid, String cid) throws UnknownClientException, UnknownProductException{
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
  }

  /**
   * Method to show all Client transactions
   * @param id represents Client's ID
   * @return a list containing all transactions associated with the given Client
   * @throws UnknownClientException if Client ID doesn't exist
   */
  public ArrayList<Sale> getClientTransactions(String id) throws UnknownClientException {
    if (_clients.containsKey(id))
      return _clients.get(id).getSales();
    throw new UnknownClientException(id);
  }


  /**
   * Method to get all current Suppliers
   * @return a Set will all Suppliers available in Store
   */
  public Set<Map.Entry<String,Supplier>> getSuppliers() {
    return _suppliers.entrySet();
  }

  /**
   * Registers a Supplier
   * @param id represents Supplier's ID
   * @param name represents Supplier's name
   * @param address represents Supplier's address
   * @throws DuplicateSupplierException if Supplier ID already exists
   */
  public void registerSupplier(String id, String name, String address) throws DuplicateSupplierException {
    if (_suppliers.containsKey(id)) {
      throw new DuplicateSupplierException(id);
    }
    _suppliers.put(id, new Supplier(id, name, address));
  }

  /**
   * @return current Store date.
   */
  public int getDate() {
    return _date;
  }

  /**
   * @param days days to be advanced in Store's date.
   * @throws InvalidDaysException if days are negative
   */
  public void advanceDate(int days) throws InvalidDaysException {
    if (days > 0) {
      _date += days;
    } else {
      throw new InvalidDaysException(days);
    }
  }

  // TODO METHODS - Return types are not defined yet (void is yet to be changed in the near future)

  public void toggleTransactions(String id){
    // TODO
  }

  public void pay(int id){
    // TODO
  }

  public void registerOrderTransaction(String supID, String pID, int amt){
    // TODO
  }

  public void registerSaleTransaction(String cID, int limDate, String pID, int amt){
    // TODO
  }
  public void showTransaction(int id){
    // TODO
  }

}
