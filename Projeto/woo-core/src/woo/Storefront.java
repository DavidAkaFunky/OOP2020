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

/**
 * Storefront: façade for the core classes.
 */
public class Storefront {

  private String _filename;

  /** The actual store. */
  private Store _store = new Store();

  /** A flag to know if store was modified after save */
  private boolean _save = true;

  public Collection<Product> getProducts() {
    return _store.getProducts();
  }

  public void registerBook(String id, String title, String author, String isbn, int price, int cValue, String sID, int amount) throws DuplicateProductException, UnknownSupplierException {
    _save = true;
    _store.registerBook(id, title, author, isbn, price, cValue, sID, amount);
  }

  public void registerBox(String id, int price, int cValue, String sID, String serviceType, int amount) throws DuplicateProductException, UnknownSupplierException, UnknownServTypeException {
    _save = true;
    _store.registerBox(id, price, cValue, sID, serviceType, amount);
  }

  public void registerContainer(String id, int price, int cValue, String sID, String serviceType, String serviceLevel, int amount) throws DuplicateProductException, UnknownSupplierException, UnknownServTypeException, UnknownServLevelException {
    _save = true;
    _store.registerContainer(id, price, cValue, sID, serviceType, serviceLevel, amount);
  }

  public void changeProductPrice(String id, int newPrice) throws UnknownProductException {
    _save = true;
    _store.changeProductPrice(id, newPrice);
  }

  public Collection<Client> getClients() {
    return _store.getClients();
  }

  public Client getClient(String id) throws UnknownClientException {
    return _store.getClient(id);
  }

  public void registerClient(String id, String name, String address) throws DuplicateClientException {
    _save = true;
    _store.registerClient(id, name, address);
  }

  public void changeClientProductNotifications(String pid, String cid) throws UnknownClientException, UnknownProductException{
    _save = true;
    _store.changeClientProductNotifications(pid, cid);
  }
  
  public List<Sale> getClientTransactions(String id) throws UnknownClientException {
    return _store.getClientTransactions(id);
  }

  public Collection<Supplier> getSuppliers() {
    return _store.getSuppliers();
  }

  public void registerSupplier(String id, String name, String address) throws DuplicateSupplierException {
    _save = true;
    _store.registerSupplier(id, name, address);
  }

  public boolean toggleSupplierTransactions(String id) throws UnknownSupplierException {
    _save = true;
    return _store.toggleSupplierTransactions(id);
  }

  public List<Order> getSupplierTransactions(String sID) throws UnknownSupplierException {
    return _store.getSupplierTransactions(sID);
  }

  public void pay(int id) throws UnknownTransactionException {
    _save = true;
    _store.pay(id);
  }

  public void registerOrderTransaction(String supID, String pID, int qty)
      throws UnknownSupplierException, UnknownProductException, InactiveSupplierException, IncorrectSupplierException {
    _save = true;
    _store.registerOrderTransaction(supID, pID, qty);
  }

  public void registerSaleTransaction(String cID, int limDate, String pID, int qty)
      throws UnknownClientException, UnknownProductException, NoEnoughStockProductException {
    _save = true;
    _store.registerSaleTransaction(cID, limDate, pID, qty);
  }

  public Transaction getTransaction(int id) throws UnknownTransactionException {
    return _store.getTransaction(id);
  }

  public List<Transaction> lookupPaymentsByClient(String cID) throws UnknownClientException {
    return _store.lookupPaymentsByClient(cID);
  }

  public List<Product> lookupProductsUnderPrice(int price) { 
    return _store.lookupProductsUnderPrice(price);
  }

  public int getDate() {
    return _store.getDate();
  }

  public int getAvailableBalance() {
    return _store.getAvailableBalance();
  }

  public int getAccountingBalance() {
    return _store.getAccountingBalance();
  }

  public void advanceDate(int days) throws InvalidDaysException {
    _save = true;
    _store.advanceDate(days);
  }


  /**
   * @throws IOException
   * @throws FileNotFoundException
   * @throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    if (getSave()) {
      ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(getFilename())));
      out.writeObject(_store);
      out.close();
      _save = false;
    }
  }

  /**
   * @param filename
   * @throws MissingFileAssociationException
   * @throws IOException
   * @throws FileNotFoundException
   */
  public void saveAs(String filename) throws MissingFileAssociationException, FileNotFoundException, IOException {
    _filename = filename;
    save();
  }

  /**
   * @param filename
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
   * @param textfile the file to be imported
   * @throws ImportFileException if something went wrong while importing
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
   * @return the given file's name
   */
  public String getFilename() {
    return _filename;
  }

  /**
   * @return the Store save status (true -> store has been modified since last save)
   */
  public boolean getSave() {
    return _save;
  }

}
