package woo;

public class Supplier {
    
    private String _id;
    private String _name;
    private String _address;
    private boolean _active = true;

    public Supplier(String id,String name, String address){
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

    public boolean isActive(){
        return _active;
    }

}
