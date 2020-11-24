package woo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.management.Notification;

public class Client implements Serializable {
    private Status _status = new NormalClient(this);
    private String _id;
    private String _name;
    private String _address;
    private int _score = 0;
    private boolean _notifiable = true;
    private Map<String,Boolean> _notifiability = new TreeMap<String,Boolean>();
    private ArrayList<Notification> _notifications = new ArrayList<Notification>();
    private ArrayList<Sale> _sales = new ArrayList<Sale>();

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
     * @return the client's received notifications
     */
    public ArrayList<Notification> getNotifications(){
        return _notifications;
    }

    /**
     * @param pID represents the ID of the product the notifiability refers to
     * @return if the client is notified about said product
     */
    public boolean isNotifiable(String pID){
        return _notifiability.get(pID);
    }

    /**
     * @return the client's notifications
     */
    public String showNotifications(){
        String str = "";
        for (Notification n: _notifications)
            str += n.toString() + "\n";
        return str;
    }

    /**
     * @return the client's sales
     */
    public ArrayList<Sale> getSales(){
        return _sales;
    }

    /**
     * @return the client's current status
     */
    public Status getStatus() {
        return _status;
    }

    /**
     * @param status represents the new status
     */
    public void setStatus(Status status) {
        _status = status;
    }

    /**
     * @param n represents if the client wants to be notified or not
     * @param pID represents the ID of the product the notifiability refers to
     */
    public void setNotifiability(boolean n, String pID){
        _notifiability.put(pID, n);
    }

    /**
     * @param score the new client's score
     */
    public void setScore(int score){
        _score = score;
    }

    /**
     * Clears all notifications
     */
    public void clearNotifications(){
        _notifications.clear();
    }

    /**
     * Makes client pay for a sale
     */
    public void pay(Sale s) {
        //Calcular o valor final da encomenda
        _status.pay(s); //Alterar o estatuto
    }

    public String toString(){
        int totalPrice = 0;
        int paidPrice = 0;
        for (Sale s: _sales){
            paidPrice += s.getBasePrice();
            totalPrice += s.getTotalPrice();
        }
        return getID() + "|" + getName() + "|" + getAddress() + "|" + getStatus() + "|" + totalPrice + "|" + paidPrice;
    }
}