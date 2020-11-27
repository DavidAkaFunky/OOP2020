package woo;

public class Sale extends Transaction{
    
    private Client _client;
    private Product _product;
    private int _limitDate;
    private int _amount;

    /**
     * @param id represents the new sale's ID
     * @param clientID represents the new sale's client ID
     * @param product represents the new sale's product
     * @param limitDate represents the new sale's limit payment date
     * @param amount represents the new sale's amount of products from the given ID
     */
    public Sale(int id, Client client, Product product, int limitDate, int amount) {
        super(id);
        _client = client;
        _product = product; 
        _limitDate = limitDate;
        _amount = amount;
    }

    @Override
    public int getBasePrice() {
        return _product.getPrice() *  _amount;
    }

    /**
     * @return the sale's real price (after taxes)
     */
    public double getTotalPrice(){
        int paymentGap = getLimitDateGap();
        int totalPrice = 0;
        int N = _product.getN();
        if (paymentGap <= -N) {
            return getBasePrice() * _client.getStatus().p1Modifier();
        } else if (-N < paymentGap && paymentGap <= 0) {
            return getBasePrice() * _client.getStatus().p2Modifier(paymentGap);
        } else if (0 < paymentGap && paymentGap <= N) {
            return getBasePrice() * _client.getStatus().p3Modifier(paymentGap);
        }
        return getBasePrice() * _client.getStatus().p3Modifier(paymentGap);
    }

    public Client getClient() {
        return _client;
    }

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

    public int getLimitDateGap(){
        return getPaymentDate() - _limitDate;
    }

    @Override
    public String toString() {
        String totalPrice = getTotalPrice() > 0 ? String.format("%.0f", getTotalPrice()) : "0.0";
        String paymentDate = getPaymentDate() != -1 ? "|" + getPaymentDate() : "";
        return getID() + "|" + getClient().getID() + "|" + getProduct().getID() + "|" + 
        getAmount() + "|" + getBasePrice() + "|" + totalPrice + "|" + getLimitDate()
        + paymentDate;
    }
}
