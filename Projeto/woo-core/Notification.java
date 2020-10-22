public class Notification {
    
    private int _productID;
    private int _productPrice;

    public Notification(int pID, int pPrice){
        _productID = pID;
        _productPrice = pPrice;
    }

    public void notify(Client c){
        c._notifications.add(this);
    }
    
}
