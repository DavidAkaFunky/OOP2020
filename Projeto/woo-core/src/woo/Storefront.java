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
    return _store.showAllProducts;
  }

  public void registerBook(int id, String title, String author, String isbn, int price, int cValue, int sId) {
    return _store.registerBook;
  }

  public void registerBox(int id, int price, int cValue, int sId) {
    return _store.registerBox;
  }

  public void registerContainer(int id, int price, int cValue, int sId) {
    return _store.registerContainer;
  }

  public void changeProductPrice(int id, int newPrice) {
    return _store.changeProductPrice;
  }

  /* PARTE DOS CLIENTES */

  public String showAllClients() {
    return _store.showAllClients;
  }

  public String showClient(int id) {
    return _store.showClient;
  }

  public void registerClient(int id, String name, String address) {
    return _store.registerClient;
  }

  public void toggleClientProductNotifications(int id){
    return _store.toggleClientProductNotifications;
  }
  
  public String showAllClientAcquisitions(int id){
    return _store.showAllClientAcquisitions;
  }

  /* PARTE DOS FORNECEDORES */

  public String showAllSuppliers() {
    return _store.showAllSuppliers;
  }

  public String showSupplier(int id) {
    return _store.showSupplier;
  }

  public void registerSupplier(int id, String name, String address) {
    return _store.registerSupplier;
  }

  public void toggleTransactions(int id){
    return _store.toggleTransactions;
  }
  
  public String showAllSupplierTransactions(int id){
    return _store.showAllSupplierTransactions;
  }

  /* PARTE DAS TRANSAÇÕES */

  public void pay(int id){
    return _store.pay;
  }

  public void registerOrderTransaction(int supId, int pId, int amt){
    return _store.registerOrderTransaction;
  }

  public void registerSaleTransaction(int cId, int limDate, int pId, int qty){
    return _store.registerSaleTransaction;
  }

  public String showTransaction(int id){
    return _store.showTransaction;
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
