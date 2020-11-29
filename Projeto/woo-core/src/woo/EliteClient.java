package woo;

/**
 * Class for Elite status Clients. These costumers don't have any
 * fines, and only have discounts.
 */

public class EliteClient extends ClientStatus {
    /**
     * Constructor.
     * 
     * @param client
     *          client being set as Elite status.
     */
    public EliteClient(Client client) { super(client); }

    /**
     * Elite Client P2 Modifier.
     * 
     * @param paymentGap
     *          difference between current date and limit date.
     * @return modifier to multiply to base sale price relative to period P2.
     */
    public double p2Modifier(int paymentGap) { return 0.9; }

    /**
     * Elite Client P3 Modifier.
     * 
     * @param paymentGap
     *          difference between current date and limit date.
     * @return modifier to multiply to base sale price relative to period P3.
     */
    public double p3Modifier(int paymentGap) { return 0.95; }

    /**
     * Elite Client P4 Modifier.
     * 
     * @param paymentGap
     *          difference between current date and limit date.
     * @return modifier to multiply to base sale price relative to period P4.
     */
    public double p4Modifier(int paymentGap) { return 1.0; }

    /**
     * Elite Client pay sale. No "level-ups" exist, only possible "level-downs".
     * 
     * @param sale
     *          sale being paid.
     */
    public void pay(Sale sale){
        int paymentGap = sale.getLimitDateGap();
        if (paymentGap >= 0)
            _client.setScore(_client.getScore() + 10 * (int) sale.getTotalPrice());
        else if (paymentGap < -15){
            _client.setStatus(new SelectionClient(_client));
            _client.setScore(_client.getScore() / 4);
        }
    }

    /**
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "ELITE";
    }

}
