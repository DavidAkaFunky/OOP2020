public class AndGate3c {

    private AndGate2 _gateA;
    private AndGate2 _gateB;

    public AndGate3c(){
        _gateA = new AndGate2();
        _gateB = new AndGate2(_gateA.getOutput(), false);
    }
    public AndGate3c(boolean bool){
        _gateA = new AndGate2(bool);
        _gateB = new AndGate2(_gateA.getOutput(), bool);
    }
    public AndGate3c(boolean bool1, boolean bool2, boolean bool3){
        _gateA = new AndGate2(bool1, bool2);
        _gateB = new AndGate2(_gateA.getOutput(), bool3);
    }

    public void setGate1(boolean bool){
        _gateA.setGate1(bool);
        _gateB = _gateB.setGate1(_gateA.getOutput());
    }
    public void setGate2(boolean bool){
        _gateA.setGate2(bool);
        _gateB = _gateB.setGate1(_gateA.getOutput());
    }
    public void setGate3(boolean bool){
        _gateB.setGate2(bool);
    }

    public boolean getGate1(){return _gateA.getGate1();}
    public boolean getGate2(){return _gateA.getGate2();}
    public boolean getGate3(){return _gateB.getGate2();}
    public boolean getOutput(){return _gateB.getOutput();}

    public boolean equals(Object other){
        if (other instanceof AndGate2){
            AndGate2 gate = (AndGate2) other;
            return _gateA.equals(gate._gateA) && getGate3() == gate.getGate3();
        }
        return false;
    }

    @Override
    public String toString(){
        return "A: " + getGate1() + " B: " + getGate2() + " C: " + getGate3();
    }
}