package woo;

import woo.exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Class Store implements a store.
 */
public class Store implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192006L;
  private Map<Integer, Transaction> _transactions = new TreeMap<Integer, Transaction>();
  private Map<String, Client> _clients = new TreeMap<String, Client>(String.CASE_INSENSITIVE_ORDER);
  private Map<String, Product> _products = new TreeMap<String, Product>(String.CASE_INSENSITIVE_ORDER);
  private Map<String, Supplier> _suppliers = new TreeMap<String, Supplier>(String.CASE_INSENSITIVE_ORDER);

  private int _date = 0;

  private int _transactionID = 0;

  private float availableBalance;
  private float accountingBalance;

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
          reader.close();
          throw new BadEntryException(input[0]);
        }
      }
      reader.close();
    } catch (IOException e) {
      throw new IOException();
    }
   }

  /**
   * @return a Collection with all Products available in store
   */
  public Collection<Product> getProducts() {
    return Collections.unmodifiableCollection(_products.values());
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
    Product product = _products.get(id);
    if (product != null) {
      throw new DuplicateProductException(id);
    }
    Supplier supplier = _suppliers.get(sID);
    if (supplier == null) {
      throw new UnknownSupplierException(sID);
    }
    _products.put(id, new Book(supplier, id, price, cValue, title, author, isbn, amount));
    /* Set all clients to receive notifications by default */
    for (Client c: _clients.values()) {
      _products.get(id).registerObserver(c);
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
    Product product = _products.get(id);
    if (product != null) {
      throw new DuplicateProductException(id);
    }
    Supplier supplier = _suppliers.get(sID);
    if (supplier == null) {
      throw new UnknownSupplierException(sID);
    }
    _products.put(id, new Box(supplier, id, price, cValue, serviceType, amount));
    for (Client c: _clients.values()) {
      _products.get(id).registerObserver(c);
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
  public void registerContainer(String id, int price, int cValue, String sID, String serviceType, String serviceLevel, int amount) throws DuplicateProductException, UnknownSupplierException, UnknownServTypeException, UnknownServLevelException {
    Product product = _products.get(id);
    if (product != null) {
      throw new DuplicateProductException(id);
    }
    Supplier supplier = _suppliers.get(sID);
    if (supplier == null) {
      throw new UnknownSupplierException(sID);
    }
    _products.put(id, new Container(supplier, id, price, cValue, amount, serviceType, serviceLevel));
    for (Client c: _clients.values()) {
      _products.get(id).registerObserver(c);
    }
  }


  /**
   * @param id represents Product's ID
   * @param newPrice represents Product's new price
   * @throws UnknownProductException if Product ID doesn't exist
   */
  public void changeProductPrice(String id, int newPrice) throws UnknownProductException {
    Product product = _products.get(id);
    if (product == null) {
      throw new UnknownProductException(id);
    } 
    product.setPrice(newPrice);
  }

  /**
   * @return a Collection will all Clients available in Store
   */
  public Collection<Client> getClients() {
    return Collections.unmodifiableCollection(_clients.values());
  }

  /**
   * @param id represents Client's ID
   * @return an instance of Client class given its ID
   * @throws UnknownClientException if Client ID doesn't exist
   */
  public Client getClient(String id) throws UnknownClientException {
    Client client = _clients.get(id);
    if (client == null) {
      throw new UnknownClientException(id);
    }
    return client;
  }

  /**
   * Registers a client.
   * @param id represents Client's ID
   * @param name represents Client's name
   * @param address represent Client's address
   * @throws DuplicateClientException if Client ID already exists
   */
  public void registerClient(String id, String name, String address) throws DuplicateClientException {
    Client client = _clients.get(id);
    if (client != null) {
      throw new DuplicateClientException(id);
    }
    Client newClient = new Client(id, name, address);
    _clients.put(id, newClient);
    for (Product p: _products.values()) {
      Observer o = (Observer) newClient;
      p.registerObserver(o);
    }
  }


  /**
   * Toggles a Product notification given a Client.
   * @param pid represents Product's ID
   * @param cid represents Client's ID
   * @throws UnknownClientException if Client ID doesn't exist
   * @throws UnknownProductException if Product ID doesn't exist
   */
  public void changeClientProductNotifications(String pid, String cid) throws UnknownClientException, UnknownProductException{
    Product product = _products.get(pid);
    if (product == null) {
      throw new UnknownProductException(pid);
    }
    Client client = _clients.get(cid);
    if (client == null) {
      throw new UnknownClientException(cid);
    }
    Observer o = (Observer) client;
    if (product.getObservers().contains(o)) {
      product.removeObserver(o);
    } else {
      product.registerObserver(o);
    }
  }

  /**
   * Method to show all Client transactions
   * @param id represents Client's ID
   * @return a list containing all transactions associated with the given Client
   * @throws UnknownClientException if Client ID doesn't exist
   */
  public List<Sale> getClientTransactions(String id) throws UnknownClientException {
    Client client = _clients.get(id);
    if (client == null) {
      throw new UnknownClientException(id);
    }
    return _clients.get(id).getClientSales();
  }

  /**
   * Registers a Supplier
   * @param id represents Supplier's ID
   * @param name represents Supplier's name
   * @param address represents Supplier's address
   * @throws DuplicateSupplierException if Supplier ID already exists
   */
  public void registerSupplier(String id, String name, String address) throws DuplicateSupplierException {
    Supplier supplier = _suppliers.get(id);
    if (supplier != null) {
      throw new DuplicateSupplierException(id);
    }
    _suppliers.put(id, new Supplier(id, name, address));
  }

  /**
   * Method to get all current Suppliers
   * @return a Collection will all Suppliers available in Store
   */
  public Collection<Supplier> getSuppliers() {
    return Collections.unmodifiableCollection(_suppliers.values());
  }

  public List<Order> getSupplierTransactions(String sID) throws UnknownSupplierException {
    Supplier supplier = _suppliers.get(sID);
    if (supplier == null) {
      throw new UnknownSupplierException(sID);
    }
    return supplier.getTransactions();
  }

  /**
   * @param id represents Supplier's ID
   * @return
   * @throws UnknownSupplierException
   */
  public boolean toggleSupplierTransactions(String id) throws UnknownSupplierException {
    Supplier supplier = _suppliers.get(id);
    if (supplier == null) {
      throw new UnknownSupplierException(id);
    }
    return supplier.toggleTransactions();
  }


  /**
   * @return current Store date.
   */
  public int getDate() {
    return _date;
  }

  public int getAvailableBalance() {
    return Math.round(availableBalance);
  }

  public int getAccountingBalance() {
    return Math.round(accountingBalance);
  }

  /**
   * @param days days to be advanced in Store's date.
   * @throws InvalidDaysException if days are negative
   */
  public void advanceDate(int days) throws InvalidDaysException {
    if (days <= 0) {
      throw new InvalidDaysException(days);
    }
    _date += days;
  }

  public Transaction getTransaction(int id) throws UnknownTransactionException {
    Transaction transaction = _transactions.get(id);
    if (transaction == null) {
      throw new UnknownTransactionException(id);
    }
    return transaction;
  }


  public void registerOrderTransaction(String supID, String pID, int qty) throws UnknownSupplierException, UnknownProductException, InactiveSupplierException,
      IncorrectSupplierException {
    Supplier supplier = _suppliers.get(supID);
    if (supplier == null) {
      throw new UnknownSupplierException(supID);
    }
    if (!supplier.isActive()) {
      throw new InactiveSupplierException(supID);
    }
    Product product = _products.get(pID);
    if (product == null) {
      throw new UnknownProductException(pID);
    }
    if (product.getSupplier() != supplier) {
      throw new IncorrectSupplierException(supID, pID);
    }
    product.addStock(qty);
    Transaction transaction = new Order(_transactionID, supplier);
    transaction.setPaymentDate(_date);
    _transactions.put(_transactionID++, transaction);
    supplier.addOrder((Order) transaction);
    accountingBalance -= product.getPrice() * qty;
    availableBalance -= product.getPrice() * qty;
  }

  public void registerSaleTransaction(String cID, int limDate, String pID, int qty) throws UnknownClientException, UnknownProductException, NoEnoughStockProductException {
    Client client = _clients.get(cID);
    if (client == null) {
      throw new UnknownClientException(cID);
    }
    Product product = _products.get(pID);
    if (product == null) {
      throw new UnknownProductException(pID);
    }
    int stock = product.getStock();
    if (stock < qty) {
      throw new NoEnoughStockProductException(cID, qty, stock);
    }
    product.removeStock(qty);
    Transaction transaction = new Sale(_transactionID, client, product, limDate, qty);
    _transactions.put(_transactionID++, transaction);
    client.addSale((Sale) transaction);
    accountingBalance += product.getPrice() * qty;
  }

  public void pay(int id) throws UnknownTransactionException {
    Sale sale = (Sale) _transactions.get(id);
    if (sale == null) {
      throw new UnknownTransactionException(id);
    }
    Client client = sale.getClient();
    client.pay(sale);
  }

  public List<Product> lookupProductsUnderPrice(int price) {
    return Collections.unmodifiableList(_products.values().stream().filter(p->p.getPrice() < price).collect(Collectors.toList()));
  }

}
