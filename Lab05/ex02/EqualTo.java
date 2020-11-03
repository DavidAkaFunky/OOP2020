public class EqualTo implements SelectionPredicate {

    int _value = 0;

    public EqualTo(int value){
        _value = value;
    }

    public boolean ok(int value){
        return _value == value;
    }
    
}