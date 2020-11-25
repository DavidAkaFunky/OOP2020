package woo;

public abstract class ClientStatus {
    protected Client _client;

    public ClientStatus(Client c) { _client = c; }

    public double p1Modifier(){ return 0.9; }
    public abstract double p2Modifier(int paymentGap);
    public abstract double p3Modifier(int paymentGap);
    public abstract double p4Modifier(int paymentGap);
    public abstract void pay(Sale s);
}
