package woo;

public class Supplier {
    
    private int _id;
    private String _name;
    private String _address;
    private boolean _active = true;

    public Supplier(int id, String name, String address){
        _id = id;
        _name = name;
        _address = address;
    }

    public int getID(){
        return _id;
    }
    
    public String getName(){
        return _name;
    }

    public String getAddress(){
        return _address;
    }

}