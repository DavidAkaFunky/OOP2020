package woo;

import woo.exceptions.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Storefront: façade for the core classes.
 */
public class Storefront {
  /** Storefront's save filename. */
  private String _filename;

  /** The actual store. */
  private Store _store = new Store();

  /** Flag on whether store has been modified since previous save. */
  private boolean _save = true;

  /**
   * @return the storefront's save filename.
   */
  public String getFilename() {
    return _filename;
  }

  /**
   * @return the storefront's save flag.
   */
  public boolean getSave() {
    return _save;
  }

  /* ------------------------------------- LOAD AND SAVE ------------------------------------- */

  /**
   * Imports a text file used to initialize app.
   * 
   * @param textfile
   *          text file being imported.
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException {
    try {
      _store.importFile(textfile);
    } catch (IOException | BadEntryException | DuplicateSupplierException | 
             UnknownSupplierException | DuplicateClientException | DuplicateProductException | 
             UnknownServTypeException | UnknownServLevelException e) {
      throw new ImportFileException(textfile);
    }
  }

  /**
   * Loads store data from a previous session from a previously saved file.
   * 
   * @param filename
   *          file being opened.
   * @throws UnavailableFileException
   * @throws IOException
   * @throws FileNotFoundException
   * @throws ClassNotFoundException
   */
  public void load(String filename) throws UnavailableFileException, FileNotFoundException, IOException, ClassNotFoundException {
    ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
    _store = (Store) in.readObject();
    in.close();
    _filename = filename;
  }

  /**
   * Saves current store data in storefront's associated filename.
   * 
   * @throws IOException
   * @throws FileNotFoundException
   * @throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    if (getSave()) {
      ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)));
      out.writeObject(_store);
      out.close();
      _save = false;
    }
  }

  /**
   * Sets storefront's filename and saves current store.
   * 
   * @param filename
   *          new storefront filename.
   * @throws MissingFileAssociationException
   * @throws IOException
   * @throws FileNotFoundException
   */
  public void saveAs(String filename) throws MissingFileAssociationException, FileNotFoundException, IOException {
    _filename = filename;
    save();
  }

  /* ------------------------------------------ DATE ------------------------------------------ */

  /**
   * Returns the store's current date.
   * 
   * @return store current date.
   */
  public int getDate() {
    return _store.getDate();
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
    _save = true;
    _store.advanceDate(days);
  }

  /* -------------------------------------- STORE BALANCE ------------------------------------- */

  /**
   * Returns store's available balance.
   * 
   * @return store available balance.
   */
  public int getAvailableBalance() {
    return _store.getAvailableBalance();
  }

  /**
   * Returns store's accounting balance.
   * 
   * @return store accounting balance.
   */
  public int getAccountingBalance() {
    return _store.getAccountingBalance();
  }

  /* ---------------------------------------- PRODUCTS ---------------------------------------- */

  /**
   * Return all the store products as an unmodifiable collection.
   * 
   * @return a collection with all store products.
   */
  public Collection<Product> getProducts() {
    return _store.getProducts();
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
    _save = true;
    _store.registerBox(id, price, cValue, sID, serviceType, amount);
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
    _save = true;
    _store.registerContainer(id, price, cValue, sID, serviceType, serviceLevel, amount);
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
    _save = true;
    _store.registerBook(id, title, author, isbn, price, cValue, sID, amount);
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
    _save = true;
    _store.changeProductPrice(id, newPrice);
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
    return _store.getClient(id);
  }

  /**
   * Returns all the store clients as an unomodifiable collection.
   * 
   * @return a collection with all store clients.
   */
  public Collection<Client> getClients() {
    return _store.getClients();
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
    _save = true;
    _store.registerClient(id, name, address);
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
    _save = true;
    _store.changeClientProductNotifications(pid, cid);
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
    return _store.getClientTransactions(id);
  }

  /* -------------------------------------- SUPPLIERS --------------------------------------- */

  /**
   * Returns all the store suppliers as an unmodifiable collection.
   * 
   * @return a collection with all store suppliers.
   */
  public Collection<Supplier> getSuppliers() {
    return _store.getSuppliers();
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
    _save = true;
    _store.registerSupplier(id, name, address);
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
    _save = true;
    return _store.toggleSupplierTransactions(id);
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
    return _store.getSupplierTransactions(sID);
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
    return _store.getTransaction(id);
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
  public void registerSaleTransaction(String cID, int limDate, String pID, int qty)
      throws UnknownClientException, UnknownProductException, NoEnoughStockProductException {
    _save = true;
    _store.registerSaleTransaction(cID, limDate, pID, qty);
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
  public void registerOrderTransaction(String supID, Map<String, Integer> products)
      throws UnknownSupplierException, UnknownProductException, InactiveSupplierException, IncorrectSupplierException {
    _save = true;
    _store.registerOrderTransaction(supID, products);
  }

  /**
   * Pays a sale given its unique transaction ID.
   * 
   * @param id
   *          sale ID.
   * @throws UnknownTransactionException
   */
  public void pay(int id) throws UnknownTransactionException {
    _save = true;
    _store.pay(id);
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
    return _store.lookupPaymentsByClient(cID);
  }

  /**
   * Returns all store products whose price is cheapear than given price as an unmodifiable list.
   * 
   * @param price
   *          reference price.
   * @return a list with all products whose price is under given price.
   */
  public List<Product> lookupProductsUnderPrice(int price) { 
    return _store.lookupProductsUnderPrice(price);
  }

}
