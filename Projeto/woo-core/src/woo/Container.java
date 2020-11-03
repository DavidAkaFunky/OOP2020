package woo;

import woo.exceptions.UnknownServTypeException;
import woo.exceptions.UnknownServLevelException;

public class Container extends Box {
    
    private String _serviceType;
    private String _serviceLevel;

    enum ServiceLevels {B4, C4, C5, DL};

    public Container(Supplier supplier, String id, int price, int criticalLevel, int amount, String serviceType, String serviceLevel) throws UnknownServTypeException, UnknownServLevelException {
        super(supplier, id, price, criticalLevel, serviceType, amount);
        if (validServiceLevel(serviceLevel)) {
            _serviceLevel = serviceLevel;
        } else {
            throw new UnknownServLevelException(serviceLevel);
        }
    }

    public String getServiceLevel(){
        return _serviceLevel;
    }

    public boolean validServiceLevel(String level) {
        for (ServiceLevels s : ServiceLevels.values()) {
            if (s.name().equals(level)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "CONTAINER|" + super.getID() + "|" + super.getSupplier().getID() 
        + "|" + super.getPrice() + "|" + super.getCriticalValue() + "|" + super.getStock() + "|" +
        super.getServiceType() + "|" + getServiceLevel();
    }
}
