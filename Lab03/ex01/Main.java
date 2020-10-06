public class Main{

    public static void main(String[] args){

        AndGate2 g1 = new AndGate2();
        AndGate2 g2 = new AndGate2(false, true);
        AndGate2 g3 = new AndGate2(true);

        System.out.println(g1.getOutput());
        System.out.println(g2.getOutput());
        System.out.println(g3.getOutput());
        System.out.println(g2.equals(g1));
        System.out.println(g2);
        g2.setGate1(true);
        System.out.println(g2);
        System.out.println(g2.equals(g3));
    
    }
}