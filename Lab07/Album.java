public class Album{

    private Map<Integer,Card> _cards;

    public Album(){
        _cards = new TreeMap<Integer,Card>();
    }

    public List<Card> getAll(){
        List<Card> lst = new LinkedList<Card>();
        lst.addAll(_cards.values());
        Collections.sort(lst);
        return lst;
    }
    public void add(Card c) throws DuplicateCardException{
        int number = c.getNumber();
        if (_cards.containsKey(number))
            throw new DuplicateCardException(number);
        else
            _cards.put(number, c); 
    }

    public void remove(int number) throws UnknownCardException{
        if (!_cards.containsKey(number))
            throw new UnknownCardException();
        else
            _cards.remove(number);    
    }

    public int getSize(){ return _cards.size();}

    public boolean equals(Object other){
        if (other instanceof Album){
            Album album = (Album) other;
            return getSize() == album.getSize();
        }
        return false;
    }
}