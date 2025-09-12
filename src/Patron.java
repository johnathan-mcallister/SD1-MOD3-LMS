import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

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

    public String getUID() {
        return this.UID;
    }

    // Generates a 7 character String with a consecutive number that grows as the list of patrons grows
    public String generateUID(ArrayList<Patron> patronList) {
        if (patronList == null || patronList.isEmpty()) {
            return "LP00001";
        } else {
            int max = Integer.parseInt(patronList.getFirst().getUID().substring(2));
            for (Patron patron : patronList) {
                if (Integer.parseInt(patron.getUID().substring(2)) > max) {
                    max = Integer.parseInt(patron.getUID());
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

    @Override
    public String toString() {
        return String.format("UID: %s\nNAME: %s, %s\nPHONE: %s\n%s\nBALANCE: $%s\nBOOKLIST: %s",this.UID, super.lastName, super.firstName, super.phoneNumber, super.address, df.format(this.odFine), this.bookList);
        //return String.format("[%s] %s, %s - %s",this.UID, super.lastName, super.firstName, super.phoneNumber);
    }

    public String toStringFull() {
        return String.format("UID: %s\nNAME: %s, %s\nPHONE: %s\n%s\nBALANCE: $%s\nBOOKLIST: %s",this.UID, super.lastName, super.firstName, super.phoneNumber, super.address, df.format(this.odFine), this.bookList);
    }
}
