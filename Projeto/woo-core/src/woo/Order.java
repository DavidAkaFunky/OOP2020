package woo;

import java.util.Map;
import java.util.Collections;
import java.util.LinkedHashMap;

public class Order extends Transaction {
    
    private Supplier _supplier;
    private int _basePrice;
    private Map<Product, Integer> _products = new LinkedHashMap<Product, Integer>();

    /**
     * @param id represents the new order's ID
     * @param supplierID represents the new order's supplier ID
     */
    public Order(int id, Supplier supplier){
        super(id);
        _supplier = supplier;
    }

    /**
     * @return the order's supplier ID
     */
    public Supplier getSupplier(){
        return _supplier;
    }

    @Override
    public int getBasePrice() {
        return _basePrice;
    }

    public void setTotalCost(int totalCost) {
        _basePrice = totalCost;
    }

    public void addProduct(Product product, int qty) {
        _products.put(product, qty);
    }

    public Map<Product, Integer> getOrderProducts() {
        return Collections.unmodifiableMap(_products);
    }

    @Override
    public String toString() {
        String base = getID() + "|" + getSupplier().getID() + "|" + getBasePrice() + "|" +
        getPaymentDate() + '\n';
        for (Map.Entry<Product, Integer> entry : _products.entrySet()) {
            base += entry.getKey().getID() + "|" + entry.getValue() + "\n";
        }
        return base;
    }

}
