package woo;

public class Sale extends Transaction{
    
    private Client _client;
    private int _totalPrice = 0;
    private int _limitDate;
    private int _amount;

    public Sale(Client client, int limitDate, int amount){
        super();
        _client = client
        _limitDate = limitDate;
        _amount = amount;
    }

}
