package woo;

public abstract class DeliveryMode {
    protected Client _client;

    public abstract void update(Notification notification);
}
