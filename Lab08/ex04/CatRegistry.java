import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;

public class CatRegistry implements Serializable{

    private Map<String, Cat> _cats = new TreeMap<String,Cat>();

    public Cat get(String name) {return _cats.get(name);}
    public void put(Cat c) {_cats.put(c.getName(), c);}
    
}