public class Paragraph implements PageElement{

    private List<TextBlock> _textBlocks = new ArrayList<TextBlock>();

    public void addTextBlock (TextBlock t){ _textBlocks.add(t); }

    public String render(){
        String text = "<paragraph>\n";
        for (TextBlock t: _textBlocks)
            text += t.render() + "\n";
        text += "</paragraph>";
        return text;
    }
    
}