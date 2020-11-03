import java.lang.Comparable;
import java.util.Comparator;

public class Table implements Comparable<Table>{

    public final static Comparator<Table> MAX_COMPARATOR = new MaxComparator();
    public final static Comparator<Table> LEN_COMPARATOR = new LenComparator();
    private int[] _vec;

    public Table(int dim){
        _vec = new int[dim];
    }

    private static class MaxComparator implements Comparator<Table>{
        @Override
        public int compare(Table t1, Table t2){
            return t1.getMax() - t2.getMax();
        }
    }

    private static class LenComparator implements Comparator<Table>{
        @Override
        public int compare(Table t1, Table t2){
            return t1.getLength() - t2.getLength();
        }
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

    public int getSum(){
        int sum = 0;
        for (int i = 0; i < _vec.length; i++)
            sum += _vec[i];
        return sum;
    }

    public int getMax(){
        int max = _vec[0];
        for (int i: _vec){
            if (i > max)
                max = i;
        }
        return max;
    }

    public int getLength(){
        return _vec.length;
    }
    
    @Override
    public int compareTo(Table other){
        return getSum() - other.getSum();
    }
    
}