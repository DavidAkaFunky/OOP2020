package woo;

import java.io.Serializable;

public class Supplier implements Serializable {
    
    private String _id;
    private String _name;
    private String _address;
    private boolean _active = true;

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

    @Override
    public String toString() {
        return getID() + "|" + getName() + "|" + getAddress() + "|";
    } 

}
