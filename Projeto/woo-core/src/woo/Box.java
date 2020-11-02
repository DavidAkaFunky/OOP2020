package woo;

import woo.exceptions.UnknownServTypeException;

public class Box extends Product {
    
    private String _serviceType;
    enum ServiceTypes {NORMAL, AIR, EXPRESS, PERSONAL};

    public Box(Supplier supplier, String id, int price, int criticalLevel, String serviceType, int amount) throws UnknownServTypeException {
        super(supplier, id, price, criticalLevel, amount);
        if (validServiceType(serviceType)) {
            _serviceType = serviceType;
        } else {
            throw new UnknownServTypeException(serviceType);
        }
    }
    
    public String getServiceType(){
        return _serviceType;
    }
    
    public boolean validServiceType(String serviceType) {
        for (ServiceTypes s : ServiceTypes.values()) {
            if (s.name().equals(serviceType)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "BOX|" + super.getID() + "|" + super.getSupplier().getID() 
        + "|" + super.getPrice() + "|" + super.getCriticalValue() + "|" + super.getStock() + "|" +
        getServiceType();
    }
}
