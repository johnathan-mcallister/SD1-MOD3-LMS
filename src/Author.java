import java.util.ArrayList;

public class Author extends Person {
    private ArrayList<Book> bodyOfWork;

    public Author() {
        super("author");
        this.bodyOfWork = new ArrayList<Book>();
    }

    public ArrayList<Book> getBodyOfWork() {
        return bodyOfWork;
    }

    public void setBodyOfWork(ArrayList<Book> bodyOfWork) {
        this.bodyOfWork = bodyOfWork;
    }

    public int compareTo(Author other) {
        int last = this.lastName.compareToIgnoreCase(other.lastName);
        if (last != 0) return last;
        return this.firstName.compareToIgnoreCase(other.firstName);
    }

    @Override
    public String toString() {
        return "";
    }
}
