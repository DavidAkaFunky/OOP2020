package woo;

import java.io.Serializable;

public class Notification implements Serializable {

    private String _event;
    private String _productID;
    private int _price;

    public Notification(String event, String productID, int price) {
        _productID = productID;
        _event = event;
        _price = price;
    }

    public String getPID() { return _productID; }
    public String getEvent() { return _event; }
    public int getPrice() { return _price; }

    @Override
    public String toString() {
        return getEvent() + "|" + getPID() + "|" + getPrice();
    }
}
