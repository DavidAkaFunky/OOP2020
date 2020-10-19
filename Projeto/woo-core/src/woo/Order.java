package woo;

public class Order extends Transaction{
    
    private Supplier _supplier;
    private Map<Product,Integer> _productMap = new HashMap<>();
    private int _totalPrice = 0;

    public Order(Supplier supplier){
        super();
        _supplier = supplier;
    }

    public void addProduct(Product product, int amount){
        _productMap.put(product, amount);
    }
}
