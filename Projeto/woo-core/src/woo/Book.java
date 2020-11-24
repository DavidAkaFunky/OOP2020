package woo;

public class Book extends Product {

    private String _title;
    private String _author;
    private String _isbn;

    public Book(Supplier supplier, String id, int price, int criticalLevel, String title, String author, String isbn, int amount){
        super(supplier, id, price, criticalLevel, amount);
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

    public int getN() { return 3; }

    @Override
    public String toString() {
        return "BOOK|" + super.getID() + "|" + super.getSupplier().getID() 
        + "|" + super.getPrice() + "|" + super.getCriticalValue() + "|" + super.getStock() + "|" +
        getTitle() + "|" + getAuthor() + "|" + getISBN();
    }
}
