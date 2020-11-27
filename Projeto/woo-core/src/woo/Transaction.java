package woo;

import java.io.Serializable;

public abstract class Transaction implements Serializable {

    private int _id;
    private int _basePrice = 0;
    private int _paymentDate = -1;
    private boolean _paid = false;

    /**
     * @param id represents the new transaction's ID
     */
    public Transaction(int id) {
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
    public abstract int getBasePrice();

    public int getPaymentDate() {
        return _paymentDate;
    }
    
    public boolean getPaymentStatus() {
        return _paid;
    }

    public void setPaymentDate(int date) {
        _paymentDate = date;
    }

    public void setPaidStatus(boolean yesno) {
        _paid = yesno;
    }

}