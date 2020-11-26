package woo;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Product implements Serializable, Observable {
    
    private String _id;
    private Supplier _supplier;
    private int _price;
    private int _criticalValue;
    private int _stock;
    
    private List<Observer> observers = new ArrayList<Observer>();

    /**
     * @param supplier represents the new product's supplier
     * @param id represents the new product's ID
     * @param price represents the new product's price
     * @param criticalValue represents the new product's critical value
     * @param amount represents the amount of units of the new product in stock
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
     * @return the product's supplier
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
     * @return the product's N (used in other methods)
     */
    public abstract int getN();


    public void addStock(int qty) {
        _stock += qty;
        if (_stock == qty) {
            notifyObservers("NEW");
        }
    }
    /**
     * @param remove represents the amount of units to remove from stock
     */
    public void removeStock(int qty) {
        _stock -= qty;
    }

    /**
     * @param price represents the product's new price
     */
    public void setPrice(int price) {
        int old = _price;
        if (price >= 0) {
            _price = price;
            if (price < old) {
                notifyObservers("BARGAIN");
            }
        }
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    public List<Observer> getObservers() {
        return Collections.unmodifiableList(observers);
    }

    public void notifyObservers(String event) {
        for (int i = 0; i < observers.size(); ++i) {
            Observer observer = (Observer) observers.get(i);
            observer.update(event, _id, _price);
        }
    }

}
