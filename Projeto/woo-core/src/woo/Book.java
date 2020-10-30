package woo;

public class Book extends Product{

    private String _title;
    private String _author;
    private String _isbn;

    public Book(Supplier supplier, String id, int price, int amount, int criticalLevel, String title, String author, String isbn){
        super(supplier, id, price, amount, criticalLevel);
        _title = title;
        _author = author;
        _isbn = isbn;
    }

    public String getTitle(){
        return _title;
    }

    public String getAuthor(){
        return _author;
    }

    public String getISBN(){
        return _isbn;
    }
}
