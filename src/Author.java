import java.util.ArrayList;

public class Author extends Person {
    private ArrayList<Book> bodyOfWork;

    public Author(String firstName, String lastName, String phoneNumber, Address address, ArrayList<Book> bodyOfWork) {
        super(firstName, lastName, phoneNumber, address);
        this.bodyOfWork = new ArrayList<Book>(bodyOfWork);
    }

    public ArrayList<Book> getBodyOfWork() {
        return bodyOfWork;
    }

    public void setBodyOfWork(ArrayList<Book> bodyOfWork) {
        this.bodyOfWork = bodyOfWork;
    }

    @Override
    public String toString() {
        return "";
    }
}
