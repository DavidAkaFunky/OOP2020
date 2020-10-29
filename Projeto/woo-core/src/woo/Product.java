package woo;

public class Product {
    
    private int _id;
    private Supplier _supplier;
    private int _price;
    private int _amount = 0;
    private int _criticalLevel;

    public Product(Supplier supplier, int id, int price, int amount, int criticalLevel){
        _id = id;
        _supplier = supplier;
        _price = price;
        _criticalLevel = criticalLevel;
    }

    public int getID(){
        return _id;
    }
    
    public Supplier getSupplier(){
        return _supplier;
    }
    
    public int getPrice(){
        return _price;
    }

    public int getAmount(){
        return _amount;
    }

    public int getCriticalLevel(){
        return _criticalLevel;
    }
    
}
