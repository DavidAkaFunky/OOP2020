package woo;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class Client contains informations about store clients. Each client
 * is identified by a key (String). It also implements Observer interface
 * to be notified about Product changes.
 */

public class Client implements Serializable, Observer {
    /** Client's status. */
    private ClientStatus _status = new NormalClient(this);

    /** Client's unique ID. */
    private String _id;

    /** Client's name. */
    private String _name;

    /** Client's address. */
    private String _address;

    /** Client's score (sale points). */
    private int _score = 0;

    /** Client's base sales total price. */
    private int _basePrices = 0;

    /** Client's paid sales amount. */
    private double _paid = 0;

    /** Client's chosen notification delivery type. */
    private DeliveryMode _mode;

    /** The list of sales associated to this client. */
    private List<Sale> _sales = new ArrayList<Sale>();

    /**
     * Calls main constructor with Default Delivery notification delivery system..
     * 
     * @param id
     *          client ID.
     * @param name
     *          client name.
     * @param address
     *          client address.
     */
    public Client(String id, String name, String address) {
        this(id, name, address, new DefaultDeliveryMode());
    }

    /**
     * Main constructor.
     * Create client.
     * 
     * @param id
     *          client ID.
     * @param name
     *          client name.
     * @param address
     *          client address.
     * @param mode
     *          client chosen delivery mode.
     */
    public Client(String id, String name, String address, DeliveryMode mode) {
        _id = id;
        _name = name;
        _address = address;
        _mode = mode;
    }

    /**
     * @return the client's ID.
     */
    public String getID(){
        return _id;
    }

    /**
     * @return the client's name.
     */
    public String getName(){
        return _name;
    }

    /**
     * @return the client's address.
     */
    public String getAddress(){
        return _address;
    }

    /**
     * @return the client's score (total points).
     */
    public int getScore(){
        return _score;
    }

    /**
     * Returns the client's sales made as an unmodifiable List.
     * 
     * @return a list with the client's sales.
     */
    public List<Sale> getClientSales() {
        return Collections.unmodifiableList(_sales);
    }

    /**
     * Adds a sale to client's sales list.
     * Also updates total sale base prices.
     * 
     * @param sale
     *          sale being added to list of client's sales.
     */
    public void addSale(Sale sale) {
        _sales.add(sale);
        _basePrices += sale.getBasePrice();
    }

    /**
     * Updates client's total paid sales amount.
     * 
     * @param salePrice
     *          price of paid sale to be added to client's total paid sales.
     */
    public void addPaidSale(double salePrice) {
        _paid += salePrice;
    }

    /**
     * Returns the client's status.
     * 
     * @return the client's current status
     */
    public ClientStatus getStatus() {
        return _status;
    }

    /**
     * Returns the client's chosen delivery mode.
     * 
     * @return the client's current delivery mode
     */
    public DeliveryMode getDeliveryMode() {
        return _mode;
    }

    /**
     * Updates client's status.
     * 
     * @param status
     *          client's new status.
     */
    public void setStatus(ClientStatus status) {
        _status = status;
    }

    /**
     * Updates client's points/score.
     * 
     * @param score 
     *          client's new score
     */
    public void setScore(int score){
        _score = score;
    }
    
    /**
     * Pays client's sale.
     * 
     * @param sale
     *          sale being paid.
     */
    public void pay(Sale sale) {
        sale.pay();
        _status.pay(sale);
        addPaidSale(sale.getTotalPrice());
    }

    /**
     * Update client using their chosen delivery mode.
     * 
     * @param notification
     *          notification being added.
     */
    public void update(String event, String pID, int price) {
        _mode.update(event, pID, price);
    }

    /**
     * Returns pending client notifications as an unmodifiable List.
     * 
     * @return a list with the client's pending notifications.
     */
    public List<Notification> getNotifications() {
        return _mode.getNotifications();
    }

    /**
     * Clears client's notifications.
     */
    public void clearNotifications() {
        _mode.clearNotifications();
    }

    /**
	 * @see java.lang.Object#toString()
	 */
    public String toString() {
        return _id + "|" + _name + "|" + _address + "|" + _status.toString() + "|" + _basePrices + "|" + (int) _paid;
    }

}