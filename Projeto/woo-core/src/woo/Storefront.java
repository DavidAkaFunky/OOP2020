package woo;

import java.io.*;
import woo.exceptions.*;

/**
 * Storefront: façade for the core classes.
 */
public class Storefront {

  /** Current filename. */
  private String _filename;

  /** The actual store. */
  private Store _store = new Store();

  //FIXME define other attributes
  //FIXME define constructor(s)
  //FIXME define other methods

  public String showAllProducts() {
    return _store.showAllProducts();
  }

  public void registerBook(int id, String title, String author, String isbn, int price, int cValue, int sID) {
    return _store.registerBook(id, title, author, isbn, price, cValue, sID);
  }

  public void registerBox(int id, int price, int cValue, int sID) {
    return _store.registerBox(id, price, cValue, sID);
  }

  public void registerContainer(int id, int price, int cValue, int sID) {
    return _store.registerContainer(id, price, cValue, sID);
  }

  public void changeProductPrice(int id, int newPrice) {
    return _store.changeProductPrice(id, newPrice);
  }

  /* PARTE DOS CLIENTES */

  public String showAllClients() {
    return _store.showAllClients;
  }

  public String showClient(int id) {
    return _store.showClient(id);
  }

  public void registerClient(int id, String name, String address) {
    return _store.registerClient(id, name, address);
  }

  public void toggleClientProductNotifications(int id){
    return _store.toggleClientProductNotifications(id);
  }
  
  public String showAllClientAcquisitions(int id){
    return _store.showAllClientAcquisitions(id);
  }

  /* PARTE DOS FORNECEDORES */

  public String showAllSuppliers() {
    return _store.showAllSuppliers;
  }

  public String showSupplier(int id) {
    return _store.showSupplier(id);
  }

  public void registerSupplier(int id, String name, String address) {
    return _store.registerSupplier(id, name, address);
  }

  public void toggleTransactions(int id){
    return _store.toggleTransactions(id);
  }
  
  public String showAllSupplierTransactions(int id){
    return _store.showAllSupplierTransactions(id);
  }

  /* PARTE DAS TRANSAÇÕES */

  public void pay(int id){
    return _store.pay(id);
  }

  public void registerOrderTransaction(int supID, int pID, int amt){
    return _store.registerOrderTransaction(supID, pID, amt);
  }

  public void registerSaleTransaction(int cID, int limDate, int pID, int qty){
    return _store.registerSaleTransaction(cID, limDate, pID, qty);
  }

  public String showTransaction(int id){
    return _store.showTransaction(id);
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
