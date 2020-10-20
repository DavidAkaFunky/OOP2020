package woo;

public abstract class Status {
    private Client _client;

    public Status(Client client) {
        _client = client;
    }

    public void changeStatus() {
        if (_client.getScore() > 2000) {
            _client.setStatus(new Selection(_client));
        }
        if (_client.getScore() > 25000) {
            _client.setStatus(new Elite(_client));
        }
    }

    public abstract String toString();
}
