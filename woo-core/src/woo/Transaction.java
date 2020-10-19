package woo;

public class Transaction {
    
    private static int _id = 0;

    public Transaction(){
        _id++;
    }
    
    public int getID(){
        return _id;
    }
}
