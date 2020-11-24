package woo;

public class Sale extends Transaction{
    
    private Client _client;
    private Product _product;
    private int _limitDate;
    private int _amount;
    private int _paymentDate;

    /**
     * @param id represents the new sale's ID
     * @param clientID represents the new sale's client ID
     * @param product represents the new sale's product
     * @param limitDate represents the new sale's limit payment date
     * @param amount represents the new sale's amount of products from the given ID
     */
    public Sale(int id, Client client, Product product, int limitDate, int amount){
        super(id);
        _client = client;
        _product = product; 
        _limitDate = limitDate;
        _amount = amount;
    }

    /**
     * @return the sale's real price (after taxes)
     */
    public double getTotalPrice(){
        int paymentGap = getLimitDateGap();
        int N = _product.getN();
        if (paymentGap <= -N)
            return getBasePrice() * _client.getStatus().p1Modifier();
        else if (-N < paymentGap && paymentGap <= 0)
            return getBasePrice() * _client.getStatus().p2Modifier(paymentGap);
        else if (0 < paymentGap && paymentGap <= N)
            return getBasePrice() * _client.getStatus().p3Modifier(paymentGap);
        return getBasePrice() * _client.getStatus().p3Modifier(paymentGap);
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
     * @return the sale's payment date
     */
    public int getPaymentDate(){
        return _paymentDate;
    }

    public int getLimitDateGap(){
        return _paymentDate - _limitDate;
    }
}
