package woo;

public class Client {

    private int _id;
    private String _name;
    private String _address;
    private int _score = 0;
    private Status _status = new Normal();

    public Client(int id, String name, String address){
        _id = id;
        _name = name;
        _address = address;
    }

    public int getID(){
        return _id;
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
        _status = status;
    }

    public void changeStatus() {
        if (_client.getScore() > 2000) {
            _client.setStatus(new Selection(_client));
        }
        if (_client.getScore() > 25000) {
            _client.setStatus(new Elite(_client));
        }
    }

}