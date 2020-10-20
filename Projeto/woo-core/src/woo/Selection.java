package woo;

public class Selection extends Status {
    
    public Selection(Client client){
        super(client);
    }

    @Override
    public String toString() {
        return "SELECTION";
    }
}
