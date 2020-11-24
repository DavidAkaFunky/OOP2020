package woo;

public abstract class Status {
    protected Client _client;

    public Status(Client c) { _client = c; }

    public double p1Modifier(){ return 0.9; }
    public abstract double p2Modifier(int paymentGap);
    public abstract double p3Modifier(int paymentGap);
    public abstract double p4Modifier(int paymentGap);
    public abstract void pay(Sale s);
}
