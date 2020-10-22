public class Notification {
    
    private String _type;
    private String _productID;
    private int _productPrice;

    public Notification(String type, String pID, int pPrice){
        _type = type;
        _productID = pID;
        _productPrice = pPrice;
    }

    public void notify(Client c){
        c._notifications.add(this);
    }
    
}
