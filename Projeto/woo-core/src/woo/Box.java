package woo;

public class Box extends Product{
    
    private String _serviceType;

    public Box(Supplier supplier, int id, int price, int amount, int criticalLevel, String serviceType){
        super(supplier, id, price, amount, criticalLevel);
        _serviceType = serviceType;
    }
    
    public String getServiceType(){
        return _serviceType;
    }
}
