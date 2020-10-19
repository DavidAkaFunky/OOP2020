package woo;

public class Product {
    
    private Supplier _supplier;
    private int _id;
    private int _price;
    private int _amount = 0;
    private int _criticalLevel;

    public Product(Supplier supplier, int id, int price, int amount, int criticalLevel){
        _supplier = supplier;
        _id = id;
        _price = price;
        _criticalLevel = criticalLevel;
    }

    public Supplier getSupplier(){
        return _supplier;
    }

    public int getID(){
        return _id;
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
