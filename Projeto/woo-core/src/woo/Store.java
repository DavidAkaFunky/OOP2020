package woo;

import woo.exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
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

  /** Store transactions. */
  private Map<Integer, Transaction> _transactions = new TreeMap<Integer, Transaction>();

  /** Store clients. */
  private Map<String, Client> _clients = new TreeMap<String, Client>(String.CASE_INSENSITIVE_ORDER);

  /** Store products. */
  private Map<String, Product> _products = new TreeMap<String, Product>(String.CASE_INSENSITIVE_ORDER);

  /** Store suppliers. */
  private Map<String, Supplier> _suppliers = new TreeMap<String, Supplier>(String.CASE_INSENSITIVE_ORDER);

  /** Current date. */
  private int _date = 0;

  /** Transaction ID. */
  private int _transactionID = 0;

  /** Company balance (starts at 0) */
  private float availableBalance = 0;
  private float accountingBalance = 0;

  /* ------------------------------------- IMPORT FILE ------------------------------------- */

  /**
   * Imports a text file used to initialize app.
   * 
   * @param textfile
   *          text file being imported.
   */
  void importFile(String txtfile) throws IOException, BadEntryException, DuplicateSupplierException, UnknownSupplierException, 
                                         DuplicateClientException, UnknownServTypeException, DuplicateProductException, UnknownServLevelException {
    BufferedReader reader = new BufferedReader(new FileReader(txtfile));
    String line;
    while ((line = reader.readLine()) != null) {
      String[] fields = line.split("\\|");
      registerFromFields(fields);
    }
    reader.close();
  }

  /**
   * Aux method to importFile.
   * 
   * @param fields
   *          array of fields containing specific input attributes.
   * @throws BadEntryException
   * @throws DuplicateSupplierException
   * @throws UnknownSupplierException
   * @throws DuplicateClientException
   * @throws UnknownServTypeException
   * @throws DuplicateProductException
   * @throws UnknownServLevelException
   */
  void registerFromFields(String[] fields) throws BadEntryException, DuplicateSupplierException, UnknownSupplierException, 
                                          DuplicateClientException, UnknownServTypeException, DuplicateProductException, UnknownServLevelException {
    Pattern patSupplier = Pattern.compile("^(SUPPLIER)");
    Pattern patClient = Pattern.compile("^(CLIENT)");
    Pattern patBox = Pattern.compile("^(BOX)");
    Pattern patContainer = Pattern.compile("^(CONTAINER)");
    Pattern patBook = Pattern.compile("^(BOOK)");
    if (patSupplier.matcher(fields[0]).matches()) {
      registerSupplier(fields[1], fields[2], fields[3]);
    } else if (patClient.matcher(fields[0]).matches()) {
      registerClient(fields[1], fields[2], fields[3]);
    } else if (patBox.matcher(fields[0]).matches()) {
      int price = Integer.parseInt(fields[4]);
      int cValue = Integer.parseInt(fields[5]);
      int amount = Integer.parseInt(fields[6]);
      registerBox(fields[1], price, cValue, fields[3], fields[2], amount);
    } else if (patContainer.matcher(fields[0]).matches()) {
      int price = Integer.parseInt(fields[5]);
      int cValue = Integer.parseInt(fields[6]);
      int amount = Integer.parseInt(fields[7]);
      registerContainer(fields[1], price, cValue, fields[4], fields[2], fields[3], amount);
    } else if (patBook.matcher(fields[0]).matches()) {
      int price = Integer.parseInt(fields[6]);
      int cValue = Integer.parseInt(fields[7]);
      int amount = Integer.parseInt(fields[8]);
      registerBook(fields[1], fields[2], fields[3], fields[4], price, cValue, fields[5], amount);
    } else {
      throw new BadEntryException(fields[0]);
    }
  }

  /* ------------------------------------------ DATE ------------------------------------------ */

  /**
   * Returns the store's current date.
   * 
   * @return store current date.
   */
  public int getDate() {
    return _date;
  }

  /**
   * Advances store's date.
   * 
   * @param days
   *          date days being advanced.
   * @throws InvalidDaysException
   *            if days <= 0.
   */
  public void advanceDate(int days) throws InvalidDaysException {
    if (days <= 0) {
      throw new InvalidDaysException(days);
    }
    _date += days;
  }

  /* -------------------------------------- STORE BALANCE ------------------------------------- */

  /**
   * Returns store's available balance.
   * 
   * @return store available balance.
   */
  public int getAvailableBalance() {
    availableBalance = 0;
    for (Transaction t : _transactions.values()) {
      if (t.getPaymentStatus() == true) {
        availableBalance += t.getTotalPrice();
      }
    }
    return Math.round(availableBalance);
  }

  /**
   * Returns store's accounting balance.
   * 
   * @return store accounting balance.
   */
  public int getAccountingBalance() {
    accountingBalance = 0;
    for (Transaction t : _transactions.values()) {
      accountingBalance += t.getTotalPrice();
    }
    return Math.round(accountingBalance);
  }

  /* ---------------------------------------- PRODUCTS ---------------------------------------- */

  /**
   * @return a Collection with all Products available in store
   */
  public Collection<Product> getProducts() {
    return Collections.unmodifiableCollection(_products.values());
  }

  /**
   * Registers a box in store.
   * 
   * @param id
   *          box product ID.
   * @param price
   *          box product price.
   * @param cValue
   *          box product critical stock level.
   * @param sID
   *          supplier ID.
   * @param serviceType
   *          box service type.
   * @param amount
   *          qty of boxes registered
   * @throws DuplicateProductException
   * @throws UnknownSupplierException
   * @throws UnknownServTypeException
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
   * Registers a container in store.
   * 
   * @param id
   *          container product ID.
   * @param price 
   *          container product price.
   * @param cValue
   *          container product critical stock level.
   * @param sID
   *          supplier ID.
   * @param serviceType
   *          container service type.
   * @param serviceLevel
   *          container service level.
   * @param amount
   *          qty of containers registered
   * @throws DuplicateProductException
   * @throws UnknownSupplierException
   * @throws UnknownServTypeException
   * @throws UnknownServLevelException
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
   * Registers a book in store.
   * 
   * @param id
   *          book product ID.
   * @param title
   *          book title.
   * @param author
   *          book author.
   * @param isbn
   *          book isbn.
   * @param price
   *          book product price.
   * @param cValue
   *          book product critical stock level.
   * @param sID
   *          supplier ID.
   * @param amount
   *          qty of books registered.
   * @throws DuplicateProductException
   * @throws UnknownSupplierException
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
   * Changes a product's price.
   * 
   * @param id
   *          product ID.
   * @param newPrice
   *          new product price.
   * @throws UnknownProductException
   */
  public void changeProductPrice(String id, int newPrice) throws UnknownProductException {
    Product product = _products.get(id);
    if (product == null) {
      throw new UnknownProductException(id);
    } 
    product.setPrice(newPrice);
  }

  /* --------------------------------------- CLIENTS ---------------------------------------- */

  /**
   * Returns a client given a client unique ID.
   * 
   * @param id
   *          client ID.
   * @return
   * @throws UnknownClientException
   */
  public Client getClient(String id) throws UnknownClientException {
    Client client = _clients.get(id);
    if (client == null) {
      throw new UnknownClientException(id);
    }
    return client;
  }
  
  /**
   * Returns all the store clients as an unomodifiable collection.
   * 
   * @return a collection with all store clients.
   */
  public Collection<Client> getClients() {
    return Collections.unmodifiableCollection(_clients.values());
  }

  /**
   * Registers a client in store.
   * 
   * @param id
   *          client ID.
   * @param name
   *          client name.
   * @param address
   *          client address.
   * @throws DuplicateClientException
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
   * Activates/Deactivates a client's specific product notifications.
   * 
   * @param pid
   *          product ID.
   * @param cid
   *          client ID.
   * @throws UnknownClientException
   * @throws UnknownProductException
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
   * Returns all the client transactions as an unmodifiable list.
   * 
   * @param id
   *          client ID.
   * @return a list with all the client transactions.
   * @throws UnknownClientException
   */
  public List<Sale> getClientTransactions(String id) throws UnknownClientException {
    Client client = _clients.get(id);
    if (client == null) {
      throw new UnknownClientException(id);
    }
    return _clients.get(id).getClientSales();
  }

  /* -------------------------------------- SUPPLIERS --------------------------------------- */

  /**
   * Returns all the store suppliers as an unmodifiable collection.
   * 
   * @return a collection with all store suppliers.
   */
  public Collection<Supplier> getSuppliers() {
    return Collections.unmodifiableCollection(_suppliers.values());
  }

  /**
   * Registers a supplier in store.
   * 
   * @param id
   *          supplier ID.
   * @param name
   *          supplier name.
   * @param address
   *          supplier address.
   * @throws DuplicateSupplierException
   */
  public void registerSupplier(String id, String name, String address) throws DuplicateSupplierException {
    Supplier supplier = _suppliers.get(id);
    if (supplier != null) {
      throw new DuplicateSupplierException(id);
    }
    _suppliers.put(id, new Supplier(id, name, address));
  }

  /**
   * Activates/Deactivates a supplier's ability to perform transactions.
   * 
   * @param id
   *          supplier ID.
   * @return true if supplier is now able to perform transactions; false, otherwise.
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
   * Returns all the supplier transactions as an unmodifiable list.
   * 
   * @param sID
   *          supplier ID.
   * @return a list with all the supplier transactions.
   * @throws UnknownSupplierException
   */
  public List<Order> getSupplierTransactions(String sID) throws UnknownSupplierException {
    Supplier supplier = _suppliers.get(sID);
    if (supplier == null) {
      throw new UnknownSupplierException(sID);
    }
    return supplier.getTransactions();
  }

  /* ------------------------------------- TRANSACTIONS ------------------------------------- */

  /**
   * Returns a transaction given a unique transaction ID.
   * 
   * @param id
   *          transaction unique ID.
   * @return a transaction given its id.
   * @throws UnknownTransactionException
   */
  public Transaction getTransaction(int id) throws UnknownTransactionException {
    Transaction transaction = _transactions.get(id);
    if (transaction == null) {
      throw new UnknownTransactionException(id);
    }
    return transaction;
  }

  /**
   * Registers a store sale.
   * 
   * @param cID
   *          client ID.
   * @param limDate
   *          limit payment date.
   * @param pID
   *          sold product ID .
   * @param qty
   *          product amount sold.
   * @throws UnknownClientException
   * @throws UnknownProductException
   * @throws NoEnoughStockProductException
   */
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
      throw new NoEnoughStockProductException(pID, qty, stock);
    }
    product.removeStock(qty);
    Sale sale = new Sale(_transactionID, client, product, limDate, qty, this);
    _transactions.put(_transactionID++, sale);
    client.addSale(sale);
  }

  /**
   * Registers a store order.
   * 
   * @param supID
   *          supplier ID.
   * @param products
   *          map containing product and its quantities ordered.
   * @throws UnknownSupplierException
   * @throws UnknownProductException
   * @throws InactiveSupplierException
   * @throws IncorrectSupplierException
   */
  public void registerOrderTransaction(String supID, Map<String, Integer> products) throws UnknownSupplierException, UnknownProductException, 
                                                                                    InactiveSupplierException, IncorrectSupplierException {
    Supplier supplier = _suppliers.get(supID);
    if (supplier == null) {
      throw new UnknownSupplierException(supID);
    }
    if (supplier.isActive() == false) {
      throw new InactiveSupplierException(supID);
    }
    for (Map.Entry<String,Integer> productID : products.entrySet()) {
      Product product = _products.get(productID.getKey());
      if (product == null) {
        throw new UnknownProductException(productID.getKey());
      }
      if (product.getSupplier() != supplier) {
        throw new IncorrectSupplierException(supID, product.getID());
      }
    }
    Order order = new Order(_transactionID, supplier);
    int totalCost = 0;
    for (Map.Entry<String,Integer> pID : products.entrySet()) {
      Product p = _products.get(pID.getKey());
      int qty = pID.getValue();
      p.addStock(qty);
      totalCost += p.getPrice() * qty;
      order.addProduct(p, qty);
    }
    order.setPaymentDate(_date);
    order.isPaid();
    order.setTotalCost(totalCost);
    _transactions.put(_transactionID++, order);
    supplier.addOrder(order);
  }

  /**
   * Pays a sale given its unique transaction ID.
   * 
   * @param id
   *          sale ID.
   * @throws UnknownTransactionException
   */
  public void pay(int id) throws UnknownTransactionException {
    Transaction transaction = _transactions.get(id);
    if (transaction == null) {
      throw new UnknownTransactionException(id);
    }
    if (transaction.getPaymentStatus() == false) {
      Sale sale = (Sale) transaction;
      Client client = sale.getClient();
      client.pay(sale);
    }
  }

  /* --------------------------------------- LOOKUPS ---------------------------------------- */

  /**
   * Returns a given client's paid sales as an unmodifiable list.
   * 
   * @param cID
   *          client ID.
   * @return a list with client's paid sales.
   * @throws UnknownClientException
   */
  public List<Sale> lookupPaymentsByClient(String cID) throws UnknownClientException {
    Client client = _clients.get(cID);
    if (client == null){
      throw new UnknownClientException(cID);
    }
    return Collections.unmodifiableList(client.getClientSales().stream().filter(s->s.getPaymentStatus()).collect(Collectors.toList()));
  }

  /**
   * Returns all store products whose price is cheapear than given price as an unmodifiable list.
   * 
   * @param price
   *          reference price.
   * @return a list with all products whose price is under given price.
   */
  public List<Product> lookupProductsUnderPrice(int price) {
    return Collections.unmodifiableList(_products.values().stream().filter(p->p.getPrice() < price).collect(Collectors.toList()));
  }

}
