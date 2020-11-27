public class Card implements Comparable<Card>{

    private int _number;
    private Image _image;

    public Card(int number, Image image){
        _number = number;
        _image = image;
    }

    public int getNumber(){ return _number;}
    public int getImage(){ return _image;}

    public int compareTo(Card other){ return _number < other.getNumber();}
}