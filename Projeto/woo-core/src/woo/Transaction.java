package woo;

import java.io.Serializable;

public class Transaction implements Serializable {

    private int _id;
    private int _basePrice = 0;

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
     * @return the transaction's original price (without added taxes)
     */
    public int getBasePrice(){
        return _basePrice;
    }
    
}