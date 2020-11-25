package woo;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Supplier implements Serializable {
    
    private String _id;
    private String _name;
    private String _address;
    private boolean _active = true;
    private List<Order> _transactions = new ArrayList<Order>();

    /**
     * @param id represents the new supplier's ID
     * @param name represents the new supplier's name
     * @param address represents the new supplier's address
     */
    public Supplier(String id,String name, String address){
        _id = id;
        _name = name;
        _address = address;
    }

    /**
     * @return the supplier's ID
     */
    public String getID(){
        return _id;
    }

    /**
     * @return the supplier's name
     */
    public String getName(){
        return _name;
    }

    /**
     * @return the supplier's address
     */
    public String getAddress(){
        return _address;
    }

    /**
     * @return whether the supplier is currently active or not
     */
    public boolean isActive(){
        return _active;
    }

    public boolean toggleTransactions() {
        if (isActive()) {
            _active = false;
            return false;
        } else {
            _active = true;
            return true;
        }
    } 
    
    public List<Order> getTransactions() {
        return Collections.unmodifiableList(_transactions);
    } 

    public void addOrder(Order t) {
        _transactions.add(t);
    }

    @Override
    public String toString() {
        return getID() + "|" + getName() + "|" + getAddress() + "|";
    }

}
