import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Patron extends Person{

    private String UID;
    private double odFine;
    private ArrayList<Book> bookList;
    final Scanner input = new Scanner(System.in);
    final DecimalFormat df = new DecimalFormat("0.00");

    public Patron(String firstName, String lastName, String phoneNumber, Address address, ArrayList<Patron> patronList) {
        super(firstName, lastName, phoneNumber, address);
        this.UID = generateUID(patronList);
        this.odFine = 0.0;
        this.bookList = new ArrayList<>();
    }

    public String getUID() {
        return this.UID;
    }

    public String generateUID(ArrayList<Patron> patronList) {
        if (patronList == null) {
            return "LP00001";
        } else {
            int max = Integer.parseInt(patronList.getFirst().UID);
            for (Patron patron : patronList) {
                if (Integer.parseInt(patron.getUID()) > max) {
                    max = Integer.parseInt(patron.getUID());
                }
            }
            max += 1;
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

    public void addODFine(double odFine) {
        this.odFine += odFine;
    }

    public ArrayList<Book> getBookList() {
        return this.bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s, %s - %s",this.UID, super.lastName, super.firstName, super.phoneNumber);
    }

    public String toStringFull() {
        return String.format("UID: %s\nNAME: %s, %s\nPHONE: %s\n%s\nBALANCE: $%s\nBOOKLIST: %s",this.UID, super.lastName, super.firstName, super.phoneNumber, super.address, df.format(this.odFine), this.bookList);
    }
}
