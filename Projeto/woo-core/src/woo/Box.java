package woo;

import woo.exceptions.UnknownServTypeException;

public class Box extends Product {

    private String _serviceType;
    enum ServiceTypes {NORMAL, AIR, EXPRESS, PERSONAL};

    /**
     * @param supplier represents the new box's supplier
     * @param id represents the new box's ID
     * @param price represents the new box's price
     * @param criticalLevel represents the new box's critical level
     * @param serviceType represents the new box's service type
     * @param amount represents the amount of units of the new box in stock
     * @throws UnknownServTypeException if the given service type is not in the list of valid ones
     */
    public Box(Supplier supplier, String id, int price, int criticalLevel, String serviceType, int amount) throws UnknownServTypeException {
        super(supplier, id, price, criticalLevel, amount);
        if (validServiceType(serviceType)) {
            _serviceType = serviceType;
        } else {
            throw new UnknownServTypeException(serviceType);
        }
    }

    /**
     * @return the box's service type
     */
    public String getServiceType(){
        return _serviceType;
    }

    /**
     * @param serviceType represents the given service type
     * @return if the service type is valid
     */
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
