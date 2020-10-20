package woo;

public class Client {

    private String _name;
    private String _address;
    private int _score = 0;
    private Status _status;

    public Client(String name, String address){
        _name = name;
        _address = address;
    }
    
    public String getName(){
        return _name;
    }

    public String getAddress(){
        return _address;
    }

    public int getScore(){
        return _score;
    }

    public String getStatus() {
        return _status.toString();
    }

    public void setStatus(Status status) {
        this._status = status;
    }

}