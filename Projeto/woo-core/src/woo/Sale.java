package woo;

public class Sale extends Transaction{
    
    private String _clientID;
    private String _productID;
    private int _basePrice = 0;
    private int _totalPrice = 0;
    private int _limitDate;
    private int _amount;
    private int _paymentDate;

    public Sale(String clientID, String productID, int limitDate, int amount){
        super();
        _clientID = clientID;
        _productID = productID; 
        _limitDate = limitDate;
        _amount = amount;
    }

    String getProductID(){
        return _productID;
    }

    int getBasePrice(){
        return _basePrice;
    }

    int getTotalPrice(){
        return _totalPrice;
    }

    int getLimitDate(){
        return _limitDate;
    }

    int getAmount(){
        return _amount;
    }

    int getPaymentDate(){
        return _paymentDate;
    }
}
