package woo;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class DefaultDeliveryMode is an app-based notification delivery system.
 * All notifications sent with this strategy are app-leveled.
 */

public class DefaultDeliveryMode implements DeliveryMode {
    /** The list of notifications associated to client. */
    private List<Notification> _notifications = new ArrayList<Notification>();

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
    @Override
    public void update(String event, String pID, int price) {
        _notifications.add(new Notification(event, pID, price));
    }

    /**
     * Clears notifications list.
     * (called when client is shown and notifications are no longer relevant)
     */
    @Override
    public void clearNotifications() {
        _notifications.clear();
    }

    /**
     * Returns pending client notifications as as unmodifiable List.
     * 
     * @return a List with the client's pending notifications.
     */
    @Override
    public List<Notification> getNotifications() {
        return Collections.unmodifiableList(_notifications);
    }
    
}
