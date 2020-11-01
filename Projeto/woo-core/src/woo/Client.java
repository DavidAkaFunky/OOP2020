package woo;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Client{

    private String _id;
    private String _name;
    private String _address;
    private int _score = 0;
    private boolean _notifiable = true;
    private Map<String,Boolean> _notifiability = new TreeMap<String,Boolean>();
    private ArrayList<Notification> _notifications = new ArrayList();
    private ArrayList<Sale> _sales = new ArrayList();
    private Status _status = new Normal();

    public Client(String id, String name, String address){
        _id = id;
        _name = name;
        _address = address;
    }

    public String getID(){
        return _id;
    }
    
    public String getName(){
        return _name;
    }

    public String getAddress(){
        return _address;
    }

    public int getScore(){
        return _score;
    }

    public ArrayList<Notification> getNotifications(){
        return _notifications;
    }

    public boolean isNotifiable(String pID){
        return _notifiability.get(pID);
    }

    public String showNotifications(){
        String str = "";
        for (Notification n: _notifications)
            str += n.toString() + "\n";
        return str;
    }

    public String showSales(){
        String str = "";
        for (Sale s: _sales)
            str += String.format("%d|%s|%s|%d|%d|%d|%d|%d\n", s.getID(), _id, s.getProductID(), s.getAmount(), s.getBasePrice(), s.getTotalPrice(), s.getLimitDate(), s.getPaymentDate());
        return str;
    }

    public String getStatus() {
        return _status.toString();
    }

    public void setStatus(Status status) {
        _status = status;
    }

    public void setNotifiability(boolean n, String pID){
        _notifiability.put(pID, n);
    }

    public void clearNotifications(){
        _notifications.clear();
    }

    public void changeStatus() {
        if (getScore() > 25000) {
            setStatus(new Elite());
        }
        else if (getScore() > 2000) {
            setStatus(new Selection());
        }
    }

    public String toString(){
        int totalPrice = 0;
        int paidPrice = 0;
        for (Sale s: _sales){
            paidPrice += s.getBasePrice();
            totalPrice += s.getTotalPrice();
        }
        return getID() + "|" + getName() + "|" + getAddress() + "|" + getStatus.toString() + "|" + totalPrice + "|" + paidPrice;
    }
}