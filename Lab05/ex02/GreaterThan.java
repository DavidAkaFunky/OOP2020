public class GreaterThan implements SelectionPredicate {

    int _value = 0;

    public GreaterThan(int value){
        _value = value;
    }

    public boolean ok(int value){
        return _value < value;
    }

}