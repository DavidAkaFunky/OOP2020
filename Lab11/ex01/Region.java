import java.util.ArrayList;

public class Region implements TaxPayer{
    private ArrayList<Company> _companies = new ArrayList<Company>();

    public Region(){
        int count = (int) (Math.random() * 100);
        for (int i = 0; i < count; i++)
            _companies.add(new Company());
    }

    public Company getEmployee(int index){ return _companies.get(index); }

    public int getSize() { return _companies.size(); }

    public void accept(FriendlyIRS irs) { irs.taxRegion(this); }
}