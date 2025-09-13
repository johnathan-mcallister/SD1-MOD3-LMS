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
        loadPatrons(patronList);
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
                    System.out.println("Import Patron List - WIP");
                    break;
                case 2:
                    Patron newPatron = new Patron(patronList);
                    patronList.add(newPatron);
                    System.out.println(newPatron);
                    writeToFile(".\\logs\\patrons.txt", newPatron);
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

    public static <T> void writeToFile(String filePath, T object) {
        if (object instanceof Patron) {
            try {
                Files.write(Paths.get(filePath), ((Patron) object).serializePatron().getBytes(),
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

    public static Patron deserializePatron(String line) {
        String[] parts = line.split(" - ");

        // Example: UID - First - Last - Phone - Address - Fine - BookIDs
        Patron p = getPatron(parts);

        // Book IDs would be in parts[6], comma-delimited
        if (parts.length > 6 && !parts[6].isBlank()) {
            String[] bookIds = parts[6].split(",");
            for (String id : bookIds) {
                // You’d need a lookup to find Book objects by ID
                Book b = new Book(null, "Unknown", "Unknown", "", id, 0, 0, 1);
                p.addBook(b);
            }
        }

        return p;
    }

    private static Patron getPatron(String[] parts) {
        String uid     = parts[0].replace("[", "").replace("]", "").trim();
        String first   = parts[1];
        String last    = parts[2];
        String phone   = parts[3];
        String unitNum = parts[4];
        String street  = parts[5];
        String city    = parts[6];
        String state   = parts[7];
        String zip     = parts[8];
        String country = parts[9];
        double fine    = Double.parseDouble(parts[10]);

        return new Patron(uid, first, last, phone, unitNum, street, city, state, zip, country, fine);
    }

    public static void loadPatrons(List<Patron> patronList) {
        Path filePath = Paths.get(".\\logs\\patrons.txt");

        if (!Files.exists(filePath)) {
            System.out.println("No patrons.txt file found. Starting with empty patron list.");
            return;
        }

        try {
            List<String> lines = Files.readAllLines(filePath);

            if (lines.isEmpty()) {
                System.out.println("patrons.txt is empty. No patrons loaded.");
                return;
            }

            for (String line : lines) {
                Patron patron = deserializePatron(line);
                patronList.add(patron);
            }

            System.out.println("Loaded " + patronList.size() + " patrons from patrons.txt.");

        } catch (IOException e) {
            System.out.println("Error reading patrons.txt: " + e.getMessage());
        }
    }
}


