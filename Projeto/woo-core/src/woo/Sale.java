package woo;

public class Sale extends Transaction{
    
    private int _clientID;
    private int _totalPrice = 0;
    private int _limitDate;
    private int _amount;

    public Sale(int clientID, int limitDate, int amount){
        super();
        _clientID = clientID;
        _limitDate = limitDate;
        _amount = amount;
    }

}
