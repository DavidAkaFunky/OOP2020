package woo;

import java.util.HashMap;

public class Order extends Transaction {
    
    private Supplier _supplier;
    private Map<Product,Integer> _productMap = new HashMap<>();
    private int _totalPrice = 0;

    public Order(Supplier supplier){
        super();
        _supplier = supplier;
    }

}
