package woo;

public class SelectionClient extends ClientStatus{
    
    public SelectionClient(Client c) { super(c); }

    public double p2Modifier(int paymentGap) { return paymentGap <= -2 ? 0.95 : 1.0; }
    public double p3Modifier(int paymentGap) { return paymentGap <= 1 ? 1.0 : 1.0 + 0.02 * paymentGap; }
    public double p4Modifier(int paymentGap) { return 1 + 0.05 * paymentGap; }

    public void pay(Sale s){
        int paymentGap = s.getLimitDateGap();
        if (paymentGap <= 0)
            _client.setScore(_client.getScore() + 10 * (int) s.getTotalPrice());
        else if (paymentGap > 2){
            _client.setScore(_client.getScore() / 10);
            _client.setStatus(new NormalClient(_client));
        }
        if (_client.getScore() > 25000)
            _client.setStatus(new EliteClient(_client));
    }

    @Override
    public String toString() {
        return "SELECTION";
    }

}
