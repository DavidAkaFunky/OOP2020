public class Main_i{

    public static void main(String[] args){

        AndGate3i g1 = new AndGate3i();
        AndGate3i g2 = new AndGate3i(false, true, true);
        AndGate3i g3 = new AndGate3i(true);

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