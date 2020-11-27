package woo;

import java.io.Serializable;

public abstract class ClientStatus implements Serializable {
    protected Client _client;

    public ClientStatus(Client c) { _client = c; }

    public double p1Modifier() { return 0.9; }
    public abstract double p2Modifier(int paymentGap);
    public abstract double p3Modifier(int paymentGap);
    public abstract double p4Modifier(int paymentGap);
    public abstract void pay(Sale s);
    
}
