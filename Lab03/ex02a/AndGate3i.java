public class AndGate3i extends AndGate2{
    
    private boolean _gate3 = false;

    public AndGate3i(){}
    public AndGate3i(boolean bool){
        super(bool);
        this._gate3 = bool;
    }
    public AndGate3i(boolean bool1, boolean bool2, boolean bool3){
        super(bool1, bool2);
        this._gate3 = bool3;
    }

    public void setGate3(boolean bool){this._gate3 = bool;}
    public boolean getGate3(){return this._gate3;}
    @Override
    public boolean getOutput(){return super.getOutput() && this._gate3;}

    @Override
    public boolean equals(Object other){
        if (other instanceof AndGate3i){
            AndGate3i gate = (AndGate3i) other;
            return super.equals(other) && this.getGate3() == gate.getGate3();
        }
        return false;
    }

    @Override
    public String toString(){
        return super() + " C: " + this._gate3;
    }
}