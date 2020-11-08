package woo;

public class Sale extends Transaction{
    
    private String _clientID;
    private String _productID;
    private int _basePrice = 0;
    private int _limitDate;
    private int _amount;
    private int _paymentDate;

    /**
     * @param id represents the new sale's ID
     * @param clientID represents the new sale's client ID
     * @param productID represents the new sale's product ID
     * @param limitDate represents the new sale's limit payment date
     * @param amount represents the new sale's amount of products from the given ID
     */
    public Sale(int id, String clientID, String productID, int limitDate, int amount){
        super(id);
        _clientID = clientID;
        _productID = productID; 
        _limitDate = limitDate;
        _amount = amount;
    }

    /**
     * @return the sale's original price (without added taxes)
     */
    public int getBasePrice(){
        return _basePrice;
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
}
