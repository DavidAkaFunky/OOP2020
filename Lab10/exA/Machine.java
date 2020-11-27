public class Machine{

    private Mode _m = new NormalMode(this);

    public void on(){ _m.on(this); }
    public void off(){ _m.off(this); }
    public void sound(){ _m.sound(this); }

    public void setMode(Mode m){ _m = m; }
    
}