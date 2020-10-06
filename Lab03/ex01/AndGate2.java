public class AndGate2{
    
    private boolean _gate1 = false;
    private boolean _gate2 = false;

    public AndGate2(){}
    public AndGate2(boolean bool){this._gate1 = this._gate2 = bool;}
    public AndGate2(boolean bool1, boolean bool2){
        this._gate1 = bool1;
        this._gate2 = bool2;
    }

    public void setGate1(boolean bool){this._gate1 = bool;}
    public void setGate2(boolean bool){this._gate2 = bool;}
    public void setGates(boolean bool1, boolean bool2){
        this._gate1 = bool1;
        this._gate2 = bool2;
    }

    public boolean getGate1(){return this._gate1;}
    public boolean getGate2(){return this._gate2;}
    public boolean getOutput(){return this._gate1 && this._gate2;}

    @Override
    public boolean equals(Object other){
        if (other instanceof AndGate2){
            AndGate2 gate = (AndGate2) other;
            return this.getGate1() == gate.getGate1() && this.getGate2() == gate.getGate2();
        }
        return false;
    }

    @Override
    public String toString(){
        return "A: " + this._gate1 + " B: " + this._gate2;
    }
}