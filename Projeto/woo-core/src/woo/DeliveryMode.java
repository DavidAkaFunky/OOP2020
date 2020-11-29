package woo;

import java.io.Serializable;
import java.util.List;

public interface DeliveryMode extends Serializable {

    /**
     * Add notification to pending notifications.
     * 
     * @param notification
     *          notification being added.
     */
    public void update(Notification notification);

    /**
     * Clears notifications array.
     * (called when client is shown and notifications are no longer relevant)
     */
    public void clearNotifications();

    /**
     * Returns pending client notifications.
     * 
     * @return a list with the client's pending notifications.
     */
    public List<Notification> getNotifications();

}
