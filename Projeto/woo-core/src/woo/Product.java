package woo;

import java.io.Serializable;

public class Product implements Serializable {
    
    private String _id;
    private Supplier _supplier;
    private int _price;
    private int _criticalValue;
    private int _stock;

    public Product(Supplier supplier, String id, int price, int criticalValue, int amount){
        _id = id;
        _supplier = supplier;
        _price = price;
        _criticalValue = criticalValue;
        _stock = amount;
    }

    public String getID(){
        return _id;
    }
    
    public Supplier getSupplier(){
        return _supplier;
    }
    
    public int getPrice(){
        return _price;
    }

    public int getCriticalValue(){
        return _criticalValue;
    }

    public int getStock() {
        return _stock;
    }

    public void removeStock(int remove) {
        _stock -= remove;
    } 
    
    public void setPrice(int price){
        _price = price;
    }
}
