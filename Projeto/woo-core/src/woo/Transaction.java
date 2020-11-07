package woo;

import java.io.Serializable;

public class Transaction implements Serializable {
    
    private int _id;

    public Transaction(int id){
        _id = id;
    }
    
    public int getID(){
        return _id;
    }
    
}