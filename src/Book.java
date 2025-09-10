public class Book {

    private Author author;
    private String title;
    private String genre;
    private String ISBN;
    private String bookId;
    private int quantityAvailable;
    private int quantityCheckedOut;
    private int numCopies;

    public Book(Author author, String title, String genre, String ISBN, String bookId, int quantityAvailable, int quantityCheckedOut, int numCopies) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.ISBN = ISBN;
        this.bookId = bookId;
        this.quantityAvailable = quantityAvailable;
        this.quantityCheckedOut = quantityCheckedOut;
        this.numCopies = numCopies;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public int getQuantityCheckedOut() {
        return quantityCheckedOut;
    }

    public void setQuantityCheckedOut(int quantityCheckedOut) {
        this.quantityCheckedOut = quantityCheckedOut;
    }

    public int getNumCopies() {
        return numCopies;
    }

    public void setNumCopies(int numCopies) {
        this.numCopies = numCopies;
    }

    @Override
    public String toString() {
        return 
    }
}
