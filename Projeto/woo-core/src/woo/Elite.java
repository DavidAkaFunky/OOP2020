package woo;

public class Elite extends Status {
    
    public Elite(Client client){
        super(client);
    }

    @Override
    public String toString() {
        return "ELITE";
    }
}
