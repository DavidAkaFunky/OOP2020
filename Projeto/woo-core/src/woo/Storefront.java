package woo;

import woo.exceptions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Storefront: façade for the core classes.
 */
public class Storefront {

  /** Current filename. */
  private String _filename;

  /** The actual store. */
  private Store _store = new Store();
  
  public String showAllProducts() {
    return _store.showAllProducts();
  }

  public void registerBook(String id, String title, String author, String isbn, int price, int cValue, String sID) throws DuplicateProductException, UnknownSupplierException {
    _store.registerBook(id, title, author, isbn, price, cValue, sID);
  }

  public void registerBox(String id, int price, int cValue, String sID, String serviceType) throws DuplicateProductException, UnknownSupplierException, UnknownServTypeException {
    _store.registerBox(id, price, cValue, sID, serviceType);
  }

  public void registerContainer(String id, int price, int cValue, String sID, String serviceType, String serviceLevel) {
    _store.registerContainer(id, price, cValue, sID, serviceType, serviceLevel);
  }

  public void changeProductPrice(String id, int newPrice) throws UnknownProductException {
    _store.changeProductPrice(id, newPrice);
  }

  /* PARTE DOS CLIENTES */

  public String showAllClients() {
    return _store.showAllClients();
  }

  public String showClient(String id) throws UnknownClientException {
    return _store.showClient(id);
  }

  public void registerClient(String id, String name, String address) throws DuplicateClientException {
    _store.registerClient(id, name, address);
  }

  public void toggleClientProductNotifications(String pid, String cid) throws UnknownClientException, UnknownProductException{
    _store.toggleClientProductNotifications(pid, cid);
  }
  
  public String showClientTransactions(String id) throws UnknownClientException {
    return _store.showClientTransactions(id);
  }

  /* PARTE DOS FORNECEDORES */

  public String showAllSuppliers() {
    return _store.showAllSuppliers();
  }

  public String showSupplier(String id) {
    return _store.showSupplier(id);
  }

  public void registerSupplier(String id, String name, String address) {
    _store.registerSupplier(id, name, address);
  }

  public void toggleTransactions(String id){
    _store.toggleTransactions(id);
  }
  
  public String showAllSupplierTransactions(String id){
    return _store.showAllSupplierTransactions(id);
  }

  /* PARTE DAS TRANSAÇÕES */

  public void pay(int id){
    _store.pay(id);
  }

  public void registerOrderTransaction(int supID, int pID, int amt){
    _store.registerOrderTransaction(supID, pID, amt);
  }

  public void registerSaleTransaction(int cID, int limDate, int pID, int qty){
    _store.registerSaleTransaction(cID, limDate, pID, qty);
  }

  public String showTransaction(int id){
    return _store.showTransaction(id);
  }

  /* PARTE DAS PESQUISAS */

  public String showProductsPrice(int price){
    return _store.showProductsPrice(price);
  }

  public String showClientBills (int cID){
    return _store.showClientBills(cID);
  }
  /**
   * @throws IOException
   * @throws FileNotFoundException
   * @throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    //FIXME implement serialization method
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
   */
  public void load(String filename) throws UnavailableFileException {
    //FIXME implement serialization method
  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException {
    try {
      _store.importFile(textfile);
    } catch (IOException | BadEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(textfile);
    }
  }

}
