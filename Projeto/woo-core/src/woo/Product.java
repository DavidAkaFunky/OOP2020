package woo;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This is an abstract class representing a Store product. All products
 * in Store have an unique ID, a Supplier, its price, critical stock levels and
 * its stock in Store. Products implement Observable interface, because we want
 * to notify Observers when products either lower their price or restock.
 */
public abstract class Product implements Serializable, Observable {
    /** Product's unique ID. */
    private String _id;

    /** Product's supplier. */
    private Supplier _supplier;

    /** Product's price. */
    private int _price;

    /** Product's critical stock level. */
    private int _criticalValue;

    /** Product's quantity in stock. */
    private int _stock;
    
    /** Array containing observers who want to be notified about this product's events. */
    private List<Observer> observers = new ArrayList<Observer>();

    /**
     * Create a product.
     * 
     * @param supplier 
     *          product supplier.
     * @param id
     *          product ID.
     * @param price 
     *          product price.
     * @param criticalValue
     *          product critical stock level.
     * @param amount 
     *          quantity being added to stock.
     */
    public Product(Supplier supplier, String id, int price, int criticalValue, int amount){
        _id = id;
        _supplier = supplier;
        _price = price;
        _criticalValue = criticalValue;
        _stock = amount;
    }

    /**
     * @return the product's ID
     */
    public String getID(){
        return _id;
    }

    /**
     * @return the product's Supplier
     */
    public Supplier getSupplier(){
        return _supplier;
    }

    /**
     * @return the product's price
     */
    public int getPrice(){
        return _price;
    }

    /**
     * @return the product's critical value
     */
    public int getCriticalValue(){
        return _criticalValue;
    }

    /**
     * @return the product's amount of units in stock
     */
    public int getStock() {
        return _stock;
    }

    /**
     * @return the specific product's payment period variable.
     */
    public abstract int getN();

    /**
     * Adds stock to product.
     * 
     * @param qty
     *          amount being added to stock.
     */
    public void addStock(int qty) {
        _stock += qty;
        if (_stock == qty) {
            notifyObservers("NEW");
        }
    }

    /**
     * Removes stock from a product.
     * 
     * @param qty
     *          amount being removed from stock.
     */
    public void removeStock(int qty) {
        _stock -= qty;
    }

    /**
     * Updates product's price.
     * 
     * @param price
     *          new product price.
     */
    public void setPrice(int price) {
        int old = _price;
        if (price > 0) {
            _price = price;
            if (price < old) {
                notifyObservers("BARGAIN");
            }
        }
    }

    /**
     * Registers an Observer to be notified about events to this product.
     * 
     * @param observer
     *          observer who wants to be notified about this product's events.
     */
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Removes an Observer as interested about this product's events.
     * 
     * @param observer
     *          observer being removd as interested about this product's events.
     */
    public void removeObserver(Observer observer) {
        int i = observers.indexOf(observer);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    /**
     * Returns a list containing all Observers interested in this product.
     * 
     * @return list with all Product Observers.
     */
    public List<Observer> getObservers() {
        return Collections.unmodifiableList(observers);
    }

    /**
     * Notifies Observers about a given event.
     * 
     * @param event
     *          event to notify Observers. 
     */
    public void notifyObservers(String event) {
        for (int i = 0; i < observers.size(); ++i) {
            Observer observer = (Observer) observers.get(i);
            observer.update(event, _id, _price);
        }
    }

    /**
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "|" + getID() + "|" + getSupplier().getID() + "|" + getPrice() + "|" + getCriticalValue() + "|" + getStock() + "|";
    }

}
