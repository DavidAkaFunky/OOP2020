package woo;

public class Container extends Product{
    
    private String _serviceType;
    private String _serviceLevel;

    public Container(Supplier supplier, String id, int price, int amount, int criticalLevel, String serviceType, String serviceLevel){
        super(supplier, id, price, amount, criticalLevel);
        _serviceType = serviceType;
        _serviceLevel = serviceLevel;
    }
    
    public String getServiceType(){
        return _serviceType;
    }

    public String getServiceLevel(){
        return _serviceLevel;
    }
}
