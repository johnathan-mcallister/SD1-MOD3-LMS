import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Patron extends Person{

    private String UID;
    private double odFine;
    private ArrayList<Book> bookList;
    private Boolean IS_BLOCKED = false;
    private static final double MAX_FINE = 250.00;
    private final DecimalFormat df = new DecimalFormat("0.00");


    public Patron(ArrayList<Patron> patronList) {
        super();
        this.UID = generateUID(patronList);
        this.odFine = 0.0;
        this.bookList = new ArrayList<>();
    }

    public Patron(String id, String fName, String lName, String phoneNumber, String unitNum, String street, String city, String state, String zipCode, String country, Double odFine) {
        super(fName, lName, phoneNumber, unitNum, street, city, state, zipCode, country);
        this.UID = id;
        this.odFine = odFine;
        this.bookList = new ArrayList<>();
    }

    public String getUID() {
        return this.UID;
    }

    // Generates a 7 character String with a consecutive number that grows as the list of patrons grows
    private String generateUID(ArrayList<Patron> patronList) {
        if (patronList == null || patronList.isEmpty()) {
            return "LP00001";
        } else {
            int max = Integer.parseInt(patronList.getFirst().getUID().substring(2));
            for (Patron patron : patronList) {
                if (Integer.parseInt(patron.getUID().substring(2)) > max) {
                    max = Integer.parseInt(patron.getUID().substring(2));
                }
            }
            max++;
            return String.format("LP%05d", max);
        }
    }

    public void setUID(ArrayList<Patron> patronList) {
        this.UID = generateUID(patronList);
    }

    public double getODFine() {
        return odFine;
    }

    public void setODFine(double odFine) {
        this.odFine = odFine;
    }

    // Ensures that the Balance never exceeds the MAX_FINE value
    public double addODFine(double newFine) {
        if (this.odFine + newFine >= MAX_FINE) {
            this.IS_BLOCKED = true;
            System.out.printf("Fine limit reached. Current fines: $%s. Cannot exceed $%s\nNew Fines incurred at this point will be forgiven,\nhowever the Patron will not be allowed to check out new books until the balance has been satisfied", df.format(odFine), df.format(MAX_FINE));
        } else{
            this.odFine += newFine;
        }
        return getODFine();
    }

    // recursive to ensure a proper integer/double value is entered
    public double subODFine(double payment) {
        if (payment <= this.odFine) {
            this.odFine -= payment;
        } else {
            if (this.odFine == 0.0) {
                System.out.printf("The Balance for %s, %s has already been satisfied.\n", super.firstName, super.lastName);
                if (this.IS_BLOCKED) {
                    this.IS_BLOCKED = false;
                }
            } else {
                System.out.println("Please enter an amount less than or equal to " + this.odFine);
                try {
                    System.out.println("Enter an amount here: ");
                    subODFine(input.nextDouble());
                } catch (InputMismatchException e) {
                    System.out.println("Please enter an integer");
                    subODFine(input.nextDouble());
                }
            }
        }
        return getODFine();
    }

    public ArrayList<Book> getBookList() {
        return this.bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public ArrayList<Book> addBook(Book book) {
        this.bookList.add(book);
        return this.bookList;
    }

    public ArrayList<Book> removeBook(Book book) {
        if (this.bookList.contains(book)) {
            this.bookList.remove(book);
            System.out.printf("%s by %s [%s] has been returned.\n",
                    book.getTitle(), book.getAuthor(), book.getBookId());
        } else {
            System.out.printf("Book with ID %s is not currently checked out.%n",
                    book.getBookId());
        }

        return this.bookList; // return the updated list
    }

    public int compareTo(Patron other) {
        int last = this.lastName.compareToIgnoreCase(other.lastName);
        if (last != 0) return last;
        return this.firstName.compareToIgnoreCase(other.firstName);
    }

    // UID - First - Last - Phone - Address - Fine - bookId1,bookId2,...
    public String serializePatron() {

        String uid     = "[" + safe(this.getUID()) + "]";
        String fName   = safe(this.getFirstName());
        String lName   = safe(this.getLastName());
        String phone   = safe(this.getPhoneNumber());
        String unitNum = safe(this.getAddress().getUnitNumber());
        String street  = safe(this.getAddress().getStreet());
        String city    = safe(this.getAddress().getCity());
        String state   = safe(this.getAddress().getState());
        String zip     = safe(this.getAddress().getZip());
        String country = safe(this.getAddress().getCountry());
        String fine    = String.format("%.2f", this.getODFine());
        String books = (this.getBookList() == null || this.getBookList().isEmpty()
                ? "null"
                : joinBookIds(this.getBookList()));

        return String.join(" - ", uid, fName, lName, phone, unitNum, street, city, state, zip, country, fine, books);
    }



    // removes new lines and existing hyphens to avoid confusing imports later
    private static String safe(String s) {
        if (s == null) return "";

        return s.replace("\r", " ")
                .replace("\n", " ")
                .replace(" - ", "—") // prevent confusing the " - " delimiter
                .trim();
    }

    private static String joinBookIds(ArrayList<Book> books) {
        if (books == null || books.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            String id = b != null ? b.getBookId() : ""; // ternary operator
            sb.append(id == null ? "" : id); // ternary operator
            if (i < books.size() - 1) sb.append(",");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s, %s - %s",this.UID, super.lastName, super.firstName, super.phoneNumber);
    }
}
