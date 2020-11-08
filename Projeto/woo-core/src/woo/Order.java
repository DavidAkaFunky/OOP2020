package woo;

import java.util.Map;
import java.util.HashMap;

public class Order extends Transaction {
    
    private String _supplierID;
    private Map<Product,Integer> _productMap = new HashMap<Product,Integer>();

    /**
     * @param id represents the new order's ID
     * @param supplierID represents the new order's supplier ID
     */
    public Order(int id, String supplierID){
        super(id);
        _supplierID = supplierID;
    }

    /**
     * @return the order's supplier ID
     */
    public String getSupplierID(){
        return _supplierID;
    }

}
