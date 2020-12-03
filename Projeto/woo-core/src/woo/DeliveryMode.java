package woo;

import java.io.Serializable;
import java.util.List;

/**
 * Interface to define Notification Delivery strategy.
 * This interface is common to all delivery types, and declares
 * 3 methods that updates notifications, clears them and returns them.
 */

public interface DeliveryMode extends Serializable {
    /**
     * Add notification to pending notifications.
     * 
     * @param event
     *          notification event (NEW or BARGAIN)
     * @param pID
     *          product ID.
     * @param price
     *          product price.
     */
    public void update(String event, String pID, int price); // May be added a String containing notification delivery type.

    /**
     * Clears notifications list.
     * (called when client is shown and notifications are no longer relevant)
     */
    public void clearNotifications();

    /**
     * Returns all pending notifications as an unmodifiable List.
     * 
     * @return a list with all pending notifications.
     */
    public List<Notification> getNotifications();
}
