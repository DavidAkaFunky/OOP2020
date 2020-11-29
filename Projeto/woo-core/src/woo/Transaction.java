package woo;

import java.io.Serializable;

/**
 * This is an abstract class representing a store transaction. Subclasses
 * refine this class in accordance to whether the store bought products or
 * sold them.
 */
public abstract class Transaction implements Serializable {
    /** Transaction's unique ID. */
    private int _id;

    /** Transaction's base price (before taxes). */
    private int _basePrice = 0;

    /** Transaction's payment date. */
    private int _paymentDate;

    /** Transaction's paid status (true if paid; false, otherwise) */
    private boolean _paid = false;

    /**
     * Create new Transaction.
     * 
     * @param id
     *          transaction ID.
     */
    public Transaction(int id) {
        _id = id;
    }

    /**
     * @return the transaction's unique ID
     */
    public int getID(){
        return _id;
    }

    /**
     * @return the transaction's payment date.
     */
    public int getPaymentDate() {
        return _paymentDate;
    }
    
    /**
     * Returns transaction paid status.
     * 
     * @return true if transaction is paid; false, otherwise.
     */
    public boolean getPaymentStatus() {
        return _paid;
    }

    /**
     * Sets transaction payment date.
     * Called upon store order or client sale payment.
     * 
     * @param date
     *          date of payment.
     */
    public void setPaymentDate(int date) {
        _paymentDate = date;
    }

    /**
     * Sets transaction as paid.
     */
    public void isPaid() {
        _paid = true;
    }

    /**
     * @return the transaction's base price (before taxes).
     */
    public abstract int getBasePrice();

    /**
     * @return the transaction's total price (after taxes).
     */
    public abstract double getTotalPrice();

}