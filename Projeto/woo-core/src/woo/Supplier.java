package woo;

public class Supplier {
    
    private String _name;
    private String _address;
    private boolean _active = true;

    public Supplier(int id, String name, String address){
        _name = name;
        _address = address;
    }
    
    public String getName(){
        return _name;
    }

    public String getAddress(){
        return _address;
    }

}
