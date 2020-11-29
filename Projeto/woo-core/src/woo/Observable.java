package woo;

/**
 * The Observable interface or so called Subject, is going to be implemented
 * by Products, because Clients (Observers) want to be notified about Products'
 * changes. Therefore, it has register and remove Observers operations and, of course,
 * to notify all Observers when Observable's state has changed (NEW or BARGAIN).
 */

public interface Observable {
    /**
     * Registers an Observer (Client) to a Product.
     * 
     * @param o
     *          client being added as an Observer.
     */
    public void registerObserver(Observer o);

    /**
     * Removes an Observer (Client) from a Product.
     * Client no longer wants to receive notifications about the Observable (Product).
     * 
     * @param o
     *          client being removed as an Observer.
     */
    public void removeObserver(Observer o);

    /**
     * Notify all observers when the Observable's state has changed.
     * 
     * @param event
     *          Observable's new state (event).
     *          can be either NEW or BARGAIN.
     */
    public void notifyObservers(String event);
}
