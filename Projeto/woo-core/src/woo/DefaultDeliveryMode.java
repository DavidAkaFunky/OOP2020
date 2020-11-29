package woo;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class DefaultDeliveryMode extends DeliveryMode {
    
    /** The list of notifications associated to this client. */
    private List<Notification> _notifications = new ArrayList<Notification>();

    /**
     * Add notification to pending notifications.
     * 
     * @param notification
     *          notification being added.
     */
    public void update(Notification notification) {
        _notifications.add(notification);
    }

    /**
     * Clears notifications array.
     * (called when client is shown and notifications are no longer relevant)
     */
    public void clearNotifications() {
        _notifications.clear();
    }

    /**
     * Returns pending client notifications.
     * 
     * @return a list with the client's pending notifications.
     */
    public List<Notification> getNotifications() {
        return _notifications;
    }
    
}
