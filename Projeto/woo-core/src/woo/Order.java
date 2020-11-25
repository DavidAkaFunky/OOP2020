package woo;

public class Order extends Transaction {
    
    private Supplier _supplier;
    private int _totalCost;

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

    public int getTotalCost() {
        return _totalCost;
    }

    public void setTotalCost(int totalCost) {
        _totalCost = totalCost;
    }

    @Override
    public String toString() {
        return getID() + "|" + getSupplier().getID() + "|" + getBasePrice() + "|" +
        getPaymentDate();
    }

}
