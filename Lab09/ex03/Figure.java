public class Figure implements PageElement{

    private List<Image> _images = new ArrayList<Image>();
    private TextBlock _text = new TextBlock("");

    public void addImage (Image i){ _images.add(i); }
    public void setCaption (TextBlock t){ _text = t; }

    public String render(){
        String text = "<figure>\n";
        for (Image i: _images)
            text += i.render() + "\n";
        text += _text.render() + "\n</figure>";
        return text;
    }

}