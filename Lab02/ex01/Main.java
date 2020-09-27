public class Main{
    public static void main(String[] args){
        Cat cat = new Cat("David", 3, 4.395);
        System.out.println(cat.equals(new Cat("David", 5, 4.395)));
        cat.setAge(5);
        System.out.println(cat.equals(new Cat("David", 5, 4.395)));
        System.out.println(cat.toString());
    }
}