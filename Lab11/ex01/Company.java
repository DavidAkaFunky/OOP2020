import java.util.ArrayList;

public class Company implements TaxPayer{
    private ArrayList<Person> _employees = new ArrayList<Person>();

    public Company(){
        int count = (int) (Math.random() * 100);
        for (int i = 0; i < count; i++)
            _employees.add(new Person());
    }

    public Person getEmployee(int index){ return _employees.get(index); }

    public int getSize() { return _employees.size(); }

    public void accept(FriendlyIRS irs) { irs.taxCompany(this); }
}