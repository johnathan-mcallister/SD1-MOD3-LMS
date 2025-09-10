import java.util.ArrayList;

public class Patron extends Person{

    private String UID;
    private ArrayList<Book> bookList;

    public Patron(String firstName, String lastName, String phoneNumber, Address address, String UID,  ArrayList<Book> bookList) {
        super(firstName, lastName, phoneNumber, address);
        this.UID = UID;
        this.bookList = new ArrayList<Book>();
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Patron{" + "UID=" + UID + ", bookList=" + bookList + '}';
    }
}
