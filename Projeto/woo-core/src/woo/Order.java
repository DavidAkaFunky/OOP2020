package woo;

import java.util.Collections;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 * Class Order is a subclass of a Transaction and it's instantiated
 * when the Store wants to restock products.
 */
public class Order extends Transaction {
    /** Order's supplier. */
    private Supplier _supplier;

    /** Order's base price. */
    private int _basePrice;

    /** Order's products. */
    private Map<Product, Integer> _products = new LinkedHashMap<Product, Integer>();

    /**
     * Create order transaction.
     * 
     * @param id
     *          transaction ID.
     * @param supplierID
     *          supplier ID.
     */
    public Order(int id, Supplier supplier) {
        super(id);
        _supplier = supplier;
    }

    /**
     * @return the order's Supplier.
     */
    public Supplier getSupplier(){
        return _supplier;
    }

    /**
     * @return the order's base price.
     */
    @Override
    public int getBasePrice() {
        return _basePrice;
    }

    /**
     * @return the order's total price (|basePrice| = |totalPrice|)
     * it returns the negative base price in order to calculate
     * the store's balance without using instanceof's.
     */
    @Override
    public double getTotalPrice() {
        return (double) -_basePrice;
    }

    /**
     * Sets order total price.
     * 
     * @param totalCost
     *          order total price.
     */
    public void setTotalCost(int totalCost) {
        _basePrice = totalCost;
    }

    /**
     * Adds a product to order.
     * 
     * @param product
     *          product being added to order.
     * @param qty
     *          amount of given product being added.
     */
    public void addProduct(Product product, int qty) {
        _products.put(product, qty);
    }

    /**
     * Returns a Map containing all order products and its quantities.
     * 
     * @return map with all order products and respetive quantities.
     */
    public Map<Product, Integer> getOrderProducts() {
        return Collections.unmodifiableMap(_products);
    }

    /**
	 * @see java.lang.Object#toString()
	 */
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
