package woo;

import java.io.Serializable;

public class Product implements Serializable {
    
    private String _id;
    private Supplier _supplier;
    private int _price;
    private int _criticalValue;
    private int _stock;

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
     * @param remove represents the amount of units to remove from stock
     */
    public void removeStock(int remove) {
        _stock -= remove;
    }

    /**
     * @param price represents the product's new price
     */
    public void setPrice(int price){
        _price = price;
    }
}
