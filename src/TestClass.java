import javax.smartcardio.ATR;
import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TestClass {

    public static final Scanner input = new Scanner(System.in);
    public static ArrayList<Patron> patronList = new ArrayList<Patron>();

    public static void main(String[] args) {
        runPatronMenu();
    }

    public static void runPatronMenu() {
        int choice = 0;
        do {
            //System.out.println("\033[h\033[2J");
            //System.out.flush();
            System.out.println("*************************************");
            System.out.println("************ [ PATRONS ] ************");
            System.out.println("*************************************");
            System.out.println("1. Import Patron List");
            System.out.println("2. Add Patron");
            System.out.println("3. Remove Patron");
            System.out.println("4. Retrieve List of Patrons");
            System.out.println("5. Exit");
            System.out.println("*************************************");
            System.out.println("Please choose an option from above to proceed:");
            String response = input.nextLine();
            try {
                choice = Integer.parseInt(response);
                if (choice < 1 || choice > 4) {
                    System.out.println("Input must be between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.printf("'%s' is an invalid input! Please enter a number.%n", response);
                choice = 0;
            }
            switch (choice) {
                case 1:
                    writeToFile(".\\logs\\patrons.txt", patronList);
                    break;
                case 2:
                    Patron newPatron = new Patron(patronList);
                    patronList.add(newPatron);
                    System.out.println(newPatron);
                    break;
                case 3:
                    System.out.print("Enter the Patron ID of the patron you would like to remove: ");
                    String removePatronID = input.nextLine().trim();

                    boolean removed = false;
                    for (int i = 0; i < patronList.size(); i++) {
                        Patron p = patronList.get(i);
                        if (p.getUID().equalsIgnoreCase(removePatronID)) {
                            patronList.remove(i);
                            removed = true;
                            break;
                        }
                    }

                    if (removed) {
                        System.out.printf("Patron [%s] has been removed from the list.%n", removePatronID);
                    } else {
                        System.out.println("Patron Not Found");
                    }

                    if (!patronList.isEmpty()) {
                        patronList.forEach(System.out::println); //method operator
                    }
                    break;
                case 4:
                    Collections.sort(patronList);

                    if (!patronList.isEmpty()) {
                        patronList.forEach(System.out::println); //method operator
                    }
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }

            if (choice != 5) {
                System.out.println("\nPress Enter to continue...");
                input.nextLine();
            }

        } while (choice != 5);
    }

    public static void writeToFile(String filePath, ArrayList<?> objects) {

        if (filePath != null && !filePath.isEmpty() && objects != null && !objects.isEmpty()) {
            objects.forEach(
                object -> {
                    if (object instanceof Patron) {
                        try {
                            Files.write(Paths.get(filePath), serializePatron((Patron) object).getBytes(),
                                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                            System.out.println("File appended successfully.");
                        } catch (IOException e) {
                            System.out.println("Error appending to file: " + e.getMessage());
                        }
                    }/* else if (object instanceof Book) {
                        try {
                            Files.write(Paths.get(filePath), object.getBytes(),
                                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                            System.out.println("File appended successfully.");
                        } catch (IOException e) {
                            System.out.println("Error appending to file: " + e.getMessage());
                        }
                    }*/
                }
            );

        }

    }

    // UID - First - Last - Phone - Address - Fine - bookId1,bookId2,...
    private static String serializePatron(Patron p) {

        String uid     = safe(p.getUID());
        String fName   = safe(p.getFirstName());
        String lName   = safe(p.getLastName());
        String phone   = safe(p.getPhoneNumber());
        String address = safe(String.valueOf(p.getAddress()));
        String fine    = String.format("%.2f", p.getODFine());
        String books   = joinBookIds(p.getBookList());

        return String.join(" - ", uid, fName, lName, phone, address, fine, books);
    }

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

}


