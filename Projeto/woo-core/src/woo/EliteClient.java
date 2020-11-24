package woo;

public class EliteClient extends Status{
    
    public EliteClient(Client c) { super(c); }

    public double p2Modifier(int paymentGap) { return 1.1; }
    public double p3Modifier(int paymentGap) { return 1.05; }
    public double p4Modifier(int paymentGap) { return 1.0; }

    public void pay(Sale s){
        int paymentGap = s.getLimitDateGap();
        if (paymentGap <= 0)
            _client.setScore(_client.getScore() + 10 * (int) s.getTotalPrice());
        else if (paymentGap > 15)
            _client.setStatus(new SelectionClient(_client));
    }

}
