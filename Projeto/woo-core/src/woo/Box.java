package woo;

import woo.exceptions.UnknownServTypeException;

/**
 * Class for box products. These products have service type besides
 * all default Product attributes.
 */

public class Box extends Product {
    /** Box service type. */
    private String _serviceType;

    /** Different box service types available. */
    private enum ServiceTypes {NORMAL, AIR, EXPRESS, PERSONAL};

    /**
     * Create box product.
     * 
     * @param supplier
     *          box supplier.
     * @param id
     *          box product ID.
     * @param price
     *          box price.
     * @param criticalLevel 
     *          box critical stock level.
     * @param serviceType
     *          box service type.
     * @param amount 
     *          box qty.
     * @throws UnknownServTypeException
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
     * @return the box's service type.
     */
    public String getServiceType(){
        return _serviceType;
    }

    /**
     * @return the box's payment period variable.
     */
    public int getN() { return 5; }

    /**
     * @param serviceType 
     *          service type being checked.
     * @return true if given service type is valid; false, otherwise.
     */
    public boolean validServiceType(String serviceType) {
        for (ServiceTypes s : ServiceTypes.values()) {
            if (s.name().equals(serviceType)) {
                return true;
            }
        }
        return false;
    }

    /**
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "BOX" + super.toString() + getServiceType();
    }
}
