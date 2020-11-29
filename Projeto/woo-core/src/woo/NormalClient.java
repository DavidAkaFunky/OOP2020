package woo;

public class NormalClient extends ClientStatus {
    /**
     * Constructor.
     * 
     * @param client
     *          client being set as Normal status.
     */
    public NormalClient(Client client) { super(client); }

    /**
     * Normal Client P2 Modifier.
     * 
     * @param paymentGap
     *          difference between current date and limit date.
     * @return modifier to multiply to base sale price relative to period P2.
     */
    public double p2Modifier(int paymentGap) { return 1.0; }

    /**
     * Normal Client P3 Modifier.
     * 5% daily fines.
     * 
     * @param paymentGap
     *          difference between current date and limit date.
     * @return modifier to multiply to base sale price relative to period P3.
     */
    public double p3Modifier(int paymentGap) { return 1.0 + 0.05 * (-paymentGap); }

    /**
     * Normal Client P4 Modifier.
     * 10% daily fines.
     * 
     * @param paymentGap
     *          difference between current date and limit date.
     * @return modifier to multiply to base sale price relative to period P4.
     */
    public double p4Modifier(int paymentGap) { return 1.0 + 0.10 * (-paymentGap); }

    /**
     * Normal Client pay sale.
     * Client can "level-up" to Selection or Elite, depending on total points adquired.
     * 
     * @param sale
     *          sale being paid.
     */
    public void pay(Sale sale) {
        int paymentGap = sale.getLimitDateGap();
        if (paymentGap >= 0) {
            _client.setScore(_client.getScore() + 10 * (int) sale.getTotalPrice());
        }
        if (_client.getScore() > 25000) {
            _client.setStatus(new EliteClient(_client));
        } else if (_client.getScore() > 2000) {
            _client.setStatus(new SelectionClient(_client));
        }
    }

    /**
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "NORMAL";
    }

}
