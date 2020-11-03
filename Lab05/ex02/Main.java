public class Main{

    public static void main (String[] args){

        Table t1 = new Table(4);
        for (int i = 0; i < t1.getSize(); i++)
            t1.setElement(i, i);
        System.out.println(t1.contains(new GreaterThan(2)));
        System.out.println(t1.contains(new EqualTo(6)));
    }
}