public class Main_c{

    public static void main(String[] args){

        AndGate3c g1 = new AndGate3c();
        AndGate3c g2 = new AndGate3c(false, true, true);
        AndGate3c g3 = new AndGate3c(true);

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