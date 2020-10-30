package woo;

import java.util.Map;
import java.util.HashMap;

public class Order extends Transaction {
    
    private String _supplierID;
    private Map<Product,Integer> _productMap = new HashMap<>();
    private int _totalPrice = 0;

    public Order(String supplierID){
        super();
        _supplierID = supplierID;
    }

}
