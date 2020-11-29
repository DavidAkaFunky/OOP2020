package woo;

public class SelectionClient extends ClientStatus{
    /**
     * Constructor.
     * 
     * @param client
     *          client being set as Selection Status.
     */
    public SelectionClient(Client client) { super(client); }

    /**
     * Selection Client P2 Modifier.
     * 
     * @param paymentGap
     *          difference between current date and limit date.
     * @return modifier to multiply to base sale price relative to period P2.
     */
    public double p2Modifier(int paymentGap) { return paymentGap >= 2 ? 0.95 : 1.0; }

    /**
     * Selection Client P3 Modifier.
     * 
     * @param paymentGap
     *          difference between current date and limit date.
     * @return modifier to multiply to base sale price relative to period P3.
     */
    public double p3Modifier(int paymentGap) { return paymentGap == -1 ? 1.0 : 1.0 + 0.02 * (-paymentGap); }

    /**
     * Selection Client P4 Modifier.
     * 
     * @param paymentGap
     *          difference between current date and limit date.
     * @return modifier to multiply to base sale price relative to period P4.
     */
    public double p4Modifier(int paymentGap) { return 1 + 0.05 * (-paymentGap); }

    /**
     * Selection Client pay sale. 
     * Client can "level-down" to Normal if payment is done 2 days after its limit.
     * Client can also "level-up" if paid within payment date limit and racks up more than 25000 points.
     * 
     * @param sale
     *          sale being paid.
     */
    public void pay(Sale s){
        int paymentGap = s.getLimitDateGap();
        if (paymentGap >= 0) {
            _client.setScore(_client.getScore() + 10 * (int) s.getTotalPrice());
        } else if (paymentGap < -2) {
            _client.setScore(_client.getScore() / 10);
            _client.setStatus(new NormalClient(_client));
        }
        if (_client.getScore() > 25000)
            _client.setStatus(new EliteClient(_client));
    }

    /**
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "SELECTION";
    }

}
