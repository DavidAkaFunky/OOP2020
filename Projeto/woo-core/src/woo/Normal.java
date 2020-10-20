package woo;

public class Normal extends Status {
    
    public Normal(Client client){
        super(client);
    }

    @Override
    public String toString() {
        return "NORMAL";
    }

}
