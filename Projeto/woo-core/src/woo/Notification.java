package woo;

public class Notification {
    
    private String _type;
    private String _productID;
    private int _productPrice;

    public Notification(String type, String pID, int pPrice){
        _type = type;
        _productID = pID;
        _productPrice = pPrice;
    }

    public String getType(){
        return _type;
    }

    public String getPID(){
        return _productID;
    }

    public int getProductPrice(){
        return _productPrice;
    }

    public void notify(Client c){
        // c.getNotifications().add(this);
    }
    
    public String toString(){
        return getType() + "|" + getPID() + "|" + getProductPrice();
    }
}
