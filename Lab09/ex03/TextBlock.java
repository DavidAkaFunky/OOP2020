public class TextBlock implements PageElement{
    
    private String _text;

    public TextBlock(String text){ _text = text; }

    public String render() { return "<textblock> " + _text + " </textblock>"; }
    
}