package woo;

import java.io.Serializable;

public class Transaction implements Serializable {

    private int _id;
    private int _totalPrice = 0;

    /**
     * @param id represents the new transaction's ID
     */
    public Transaction(int id){
        _id = id;
    }

    /**
     * @return the transaction's ID
     */
    public int getID(){
        return _id;
    }

    /**
     * @return the transaction's real price (after taxes)
     */
    public int getTotalPrice(){
        return _totalPrice;
    }
    
}