package woo;

/**
 * Class for book products. These products have title, author and isbn
 * besides all default Product class attributes.
 */
public class Book extends Product {
    /** Book's title. */
    private String _title;

    /** Book's author. */
    private String _author;

    /** Book's ISBN. */
    private String _isbn;

    /**
     * Create book product.
     * 
     * @param supplier
     *          book supplier.
     * @param id
     *          book product ID.
     * @param price
     *          book price.
     * @param criticalLevel
     *          book critical stock level.
     * @param title
     *          book title.
     * @param author
     *          book author.
     * @param isbn
     *          book isbn.
     * @param amount
     *          book qty.
     */
    public Book(Supplier supplier, String id, int price, int criticalLevel, String title, String author, String isbn, int amount){
        super(supplier, id, price, criticalLevel, amount);
        _title = title;
        _author = author;
        _isbn = isbn;
    }

    /**
     * @return the book's title.
     */
    public String getTitle(){
        return _title;
    }

    /**
     * @return the book's author.
     */
    public String getAuthor(){
        return _author;
    }

    /**
     * @return the book's isbn.
     */
    public String getISBN(){
        return _isbn;
    }

    /**
     * @return the book's payment period variable.
     */
    public int getN() { return 3; }

    /**
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "BOOK" + super.toString() + getTitle() + "|" + getAuthor() + "|" + getISBN();
    }
}
