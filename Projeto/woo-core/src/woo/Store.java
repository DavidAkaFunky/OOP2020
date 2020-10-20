package woo;

import java.io.*;
import woo.exceptions.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class Store implements a store.
 */
public class Store implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192006L;
  private Map<Integer, Transaction> _transactions = new TreeMap<Integer, Transaction>();
  private Map<Integer, Client> _clients = new TreeMap<Integer, Client>();

  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException /* FIXME maybe other exceptions */ {
    //FIXME implement method
  }

  public void registerBox(int id, int price, int cValue, int sId) {
    
  }

  /* PARTE DOS CLIENTES */

  public String showAllClients() {
    /* IDEIA: Iterar por todos os clientes do TreeMap carregado a partir do file */
    return "";
  }

  public String showClient(int id) {
    /* IDEIA: A partir do id, ir buscar ao TreeMap o cliente a partir do ID e chamar o toString */
    return "";
  }

  public void registerClient(int id, String name, String address) {
    /* IDEIA: Criar cliente e adicioná-lo à TreeMap de passageiros */
    /* Lançar exceção se o id é repetido */
  }

  public void 
  

}
