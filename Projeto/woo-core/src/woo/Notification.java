package woo;

import java.io.Serializable;

/**
 * Class Notification is set to be instantiated when a Client 
 * wants to be received about specific product events, like restock
 * or sales.
 */

public class Notification implements Serializable {
    /** Notification event - NEW or BARGAIN */
    private String _event;

    /** Notification's product ID. */
    private String _productID;

    /** Notification's price. */
    private int _price;

    /**
     * Constructor.
     * 
     * @param event
     *          notifcation event.
     * @param productID
     *          notification's product ID.
     * @param price
     *          notifications' product price.
     */
    public Notification(String event, String productID, int price) {
        _event = event;
        _productID = productID;
        _price = price;
    }

    /**
     * @return notification's event.
     */
    public String getEvent() { return _event; }

    /**
     * @return notification's product ID.
     */
    public String getPID() { return _productID; }

    /**
     * @return notification's product price.
     */
    public int getPrice() { return _price; }


    /**
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return getEvent() + "|" + getPID() + "|" + getPrice();
    }
}
