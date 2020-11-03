package woo;

import java.io.Serializable;

public class Transaction implements Serializable {
    
    private int _id = 0;

    public Transaction(){
        _id++;
    }
    
    public int getID(){
        return _id;
    }
    
}