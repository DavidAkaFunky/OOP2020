package woo;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class DefaultDeliveryMode implements DeliveryMode {
    
    /** The list of notifications associated to this client. */
    private List<Notification> _notifications = new ArrayList<Notification>();

    public void update(Notification notification) {
        _notifications.add(notification);
    }

    public void clearNotifications() {
        _notifications.clear();
    }

    public List<Notification> getNotifications() {
        return Collections.unmodifiableList(_notifications);
    }
    
}
