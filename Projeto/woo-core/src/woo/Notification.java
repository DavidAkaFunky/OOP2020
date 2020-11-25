package woo;

public class Notification {

    private String _productID;
    private String _event;
    private int _price;

    public Notification(String productID, String event, int price) {
        _productID = productID;
        _event = event;
        _price = price;
    }

    public String getPID() { return _productID; }
    public String getEvent() { return _event; }
    public int getPrice() { return _price; }

    @Override
    public String toString() {
        return getPID() + "|" + getEvent() + "|" + getPrice();
    }
}
