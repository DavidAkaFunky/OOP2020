package woo;

/**
 * The Observer interface is going to be implemented by all the Observers
 * (in this case, for now, Clients are the only ones), so they all have to 
 * implement the update() method.
 */

public interface Observer {
    /**
     * Adds a notification to array of Client notifications.
     * 
     * @param notification
     *          notification being added.
     */
    public void update(Notification notification);
}
