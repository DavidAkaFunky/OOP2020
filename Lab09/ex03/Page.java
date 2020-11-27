import java.util.List;
import java.util.ArrayList;

public class Page implements PageElement {

    private List<PageElement> _elements = new ArrayList<PageElement>();

    public void addPageElement(PageElement e){ _elements.add(e); }

    public String render(){
        String text = "<page>\n";
        for (PageElement e: _elements)
            text += e.render() + "\n";
        text += "</page>";
        return text;
    }

}