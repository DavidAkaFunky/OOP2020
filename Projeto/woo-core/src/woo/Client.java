package woo;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Client implements Serializable, Observer {
    /* Client status. */
    private ClientStatus _status = new NormalClient(this);

    /* Client ID. */
    private String _id;

    /* Client name. */
    private String _name;

    /* Client address. */
    private String _address;

    /* Client score. */
    private int _score = 0;

    private List<Sale> _sales = new ArrayList<Sale>();

    private List<Notification> _notifications = new ArrayList<Notification>();

    /**
     * @param id represents the new client's ID
     * @param name represents the new client's name
     * @param address represents the new client's address
     */
    public Client(String id, String name, String address){
        _id = id;
        _name = name;
        _address = address;
    }

    /**
     * @return the client's ID
     */
    public String getID(){
        return _id;
    }

    /**
     * @return the client's name
     */
    public String getName(){
        return _name;
    }

    /**
     * @return the client's address
     */
    public String getAddress(){
        return _address;
    }

    /**
     * @return the client's score
     */
    public int getScore(){
        return _score;
    }

    /**
     * @return the client's sales
     */
    public List<Sale> getClientSales() {
        return Collections.unmodifiableList(_sales);
    }

    public void addSale(Sale s) {
        _sales.add(s);
    }

    /**
     * @return the client's current status
     */
    public ClientStatus getStatus() {
        return _status;
    }

    /**
     * @param status represents the new status
     */
    public void setStatus(ClientStatus status) {
        _status = status;
    }

    /**
     * @param score the new client's score
     */
    public void setScore(int score){
        _score = score;
    }
    
    /**
     * Makes client pay for a sale
     */
    public void pay(Sale s) {
        //Calcular o valor final da encomenda
        _status.pay(s); //Alterar o estatuto
    }

    public void update(String productID, String event, int price) {
        _notifications.add(new Notification(productID, event, price));
    }

    public void clearNotifications() {
        _notifications.clear();
    }

    public List<Notification> getNotifications() {
        return Collections.unmodifiableList(_notifications);
    }

    public String toString(){
        int totalPrice = 0;
        int paidPrice = 0;
        for (Sale s: _sales){
            paidPrice += s.getBasePrice();
            totalPrice += s.getTotalPrice();
        }
        return getID() + "|" + getName() + "|" + getAddress() + "|" + getStatus().toString() + "|" + totalPrice + "|" + paidPrice;
    }

}