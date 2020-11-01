package woo;

public class Box extends Product{
    
    private String _serviceType;

    public Box(Supplier supplier, String id, int price, int criticalLevel, String serviceType){
        super(supplier, id, price, criticalLevel);
        _serviceType = serviceType;
    }
    
    public String getServiceType(){
        return _serviceType;
    }
}
