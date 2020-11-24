package woo;

public class NormalClient extends Status {
    
    public NormalClient(Client c) { super(c); }

    public double p2Modifier(int paymentGap) { return 1.0; }
    public double p3Modifier(int paymentGap) { return 1.0 + 0.05 * paymentGap; }
    public double p4Modifier(int paymentGap) { return 1.0 + 0.1 * paymentGap; }

    public void pay(Sale s){
        int paymentGap = s.getLimitDateGap();
        if (paymentGap <= 0)
            _client.setScore(_client.getScore() + 10 * (int) s.getTotalPrice());
        if (_client.getScore() > 25000)
            _client.setStatus(new EliteClient(_client));
        else if (_client.getScore() > 2500)
            _client.setStatus(new SelectionClient(_client));
    }

}
