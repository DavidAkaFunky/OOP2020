public class Table {

    private int[] _vec;

    public Table(int dim){
        _vec = new int[dim];
    }

    public int getSize(){
        return _vec.length;
    }

    public int getElement(int index){
        if (index < _vec.length)
            return _vec[index];
    }

    public void setElement(int index, int value){
        if (index < _vec.length)
            _vec[index] = value;
    }

    public void setAllElements(int value){
        for (int i = 0; i < _vec.length; i++)
            _vec[i] = value;
    }

    public boolean contains(SelectionPredicate p){
        for (int i = 0; i < _vec.length; i++)
            if (p.ok(_vec[i]))
                return true;
        return false;
    }
}