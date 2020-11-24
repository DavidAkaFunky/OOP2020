package woo;

import woo.exceptions.UnknownServTypeException;
import woo.exceptions.UnknownServLevelException;

public class Container extends Box {
    
    private String _serviceLevel;

    private enum ServiceLevels {B4, C4, C5, DL};

    /**
     * @param supplier represents the new container's supplier
     * @param id represents the new container's ID
     * @param price represents the new container's price
     * @param criticalLevel represents the new container's critical level
     * @param amount represents the amount of units of the new container in stock
     * @param serviceType represents the new container's service type
     * @param serviceLevel represents the new container's service level
     * @throws UnknownServTypeException if the given service type is not in the list of valid ones
     * @throws UnknownServLevelException if the given service level is not in the list of valid ones
     */
    public Container(Supplier supplier, String id, int price, int criticalLevel, int amount, String serviceType, String serviceLevel) throws UnknownServTypeException, UnknownServLevelException {
        super(supplier, id, price, criticalLevel, serviceType, amount);
        if (validServiceLevel(serviceLevel)) {
            _serviceLevel = serviceLevel;
        } else {
            throw new UnknownServLevelException(serviceLevel);
        }
    }

    /**
     * @return the container's service level
     */
    public String getServiceLevel(){
        return _serviceLevel;
    }

    public int getN() { return 8; }

    /**
     * @param level represents the given service level
     * @return if the service level is valid
     */
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
