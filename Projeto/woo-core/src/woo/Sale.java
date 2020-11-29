package woo;

/**
 * Class Sale is a subclass of a Transaction and it's instantiated
 * when the Store sells products to costumers (Clients).
 */
public class Sale extends Transaction{
    /** Client who made the Store sale. */
    private Client _client;

    /** Store where the Sale was made. */
    private Store _store;

    /** Product sold to Client. */
    private Product _product;

    /** Limit date to sale payment. */
    private int _limitDate;

    /** Amount of Product demanded by Client. */
    private int _amount;

    /** Total paid sale price. */
    private double _paidPrice;

    /**
     * Create sale.
     * 
     * @param id
     *          transaction ID.
     * @param clientID
     *          client ID.
     * @param product
     *          product bought.
     * @param limitDate
     *          limit date to pay (no fines)
     * @param amount
     *          qty of product bought.
     */
    public Sale(int id, Client client, Product product, int limitDate, int amount, Store store) {
        super(id);
        _client = client;
        _product = product; 
        _limitDate = limitDate;
        _amount = amount;
        _store = store;
    }

    /**
     * @return the sale base price (before taxes).
     */
    @Override
    public int getBasePrice() {
        return _product.getPrice() *  _amount;
    }

    /**
     * @return the sale's total price (after taxes).
     * the value returned from this funtion depends on the Client status
     * and the date where the payment was made.
     */
    @Override
    public double getTotalPrice(){
        if (getPaymentStatus()) { return _paidPrice; }
        int N = _product.getN();
        int paymentGap = getLimitDateGap(); 
        if (paymentGap >= 0) {
            if (paymentGap >= N) {
                return getBasePrice() * _client.getStatus().p1Modifier();
            } else {
                return getBasePrice() * _client.getStatus().p2Modifier(paymentGap);
            }
        } else {
            paymentGap *= -1;
            if (0 < paymentGap && paymentGap <= N) {
                return getBasePrice() * _client.getStatus().p3Modifier(-paymentGap);
            } else {
                return getBasePrice() * _client.getStatus().p4Modifier(-paymentGap);
            }
        }
    }

    /**
     * @return the sale's client.
     */
    public Client getClient() {
        return _client;
    }

    /**
     * @return the sale's product.
     */
    public Product getProduct() {
        return _product;
    }

    /**
     * @return the sale's limit payment date
     */
    public int getLimitDate(){
        return _limitDate;
    }

    /**
     * @return the sale's amount of products
     */
    public int getAmount(){
        return _amount;
    }

    /**
     * Returns the difference between limit payment date and store's current date.
     * 
     * @return >= 0 if paid on time ; < 0 fines may be applied.
     */
    public int getLimitDateGap() {
        return _limitDate - _store.getDate();
    }

    /**
     * Updates sale paidPrice and paymentDate and sets sale as paid.
     */
    public void pay() {
        _paidPrice = getTotalPrice();
        setPaymentDate(_store.getDate());
        isPaid();
    }

    /**
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        String totalPrice = getTotalPrice() > 0 ? String.format("%.0f", getTotalPrice()) : "0";
        String paymentDate = getPaymentStatus() == true ? "|" + getPaymentDate() : "";
        return getID() + "|" + getClient().getID() + "|" + getProduct().getID() + "|" + 
        getAmount() + "|" + getBasePrice() + "|" + totalPrice + "|" + getLimitDate()
        + paymentDate;
    }
}
