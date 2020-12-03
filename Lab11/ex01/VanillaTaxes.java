public VanillaTaxes implements FriendlyIRS {

    public double taxPerson(Person p) {return 1;}

    public double taxCompany(Company c){
        double total = 0;
        for (int i = 0; i < c.getSize(); i++)
            total += c.getEmployee(i).accept(this);
        return total;
    }

    public double taxRegion(Region r){
        double total = 0;
        for (int i = 0; i < r.getSize(); i++)
            total += r.getCompany(i).accept(this);
        return total;
    }

}