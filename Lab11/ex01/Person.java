public class Person implements TaxPayer {
    public void accept(FriendlyIRS irs) { irs.taxPerson(this); }
}