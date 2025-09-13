/***********************************************************************************************************************
 * CEN 3024C - Software Development 1
 * Professor: Dr. Lisa Macon
 * Course ID: 202610
 * CRN: 14877
 * Semester: Fall 2025
 *
 * Date: 09/09/25
 * Written by: Johnathan McAllister /s
 * Purpose: Library Management System
 *
 ***********************************************************************************************************************
 */

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    // used for collecting responses from end user
    public static final Scanner input = new Scanner(System.in);
    // instantiating patronList
    public static ArrayList<Patron> patronList = new ArrayList<Patron>();
    // setting path for text file
    public static final Path patronFile = Path.of(".\\logs\\patrons.txt");

    public static void main(String[] args) {
        loadPatrons(patronList); // importing text file to memory
        runPatronMenu(); // opens patron menu
    }

    public static void runPatronMenu() {

        // instantiating choice
        int choice = 0;
        do {

            System.out.println("*************************************");
            System.out.println("************ [ PATRONS ] ************");
            System.out.println("*************************************");
            System.out.println("1. Add Patron");
            System.out.println("2. Remove Patron");
            System.out.println("3. Retrieve List of Patrons");
            System.out.println("4. Exit");
            System.out.println("*************************************");
            System.out.println("Please choose an option from above to proceed:");

            choice = input.nextInt(); // only accepts an integer

            if (choice < 1 || choice > 4) {
                System.out.println("Input must be between 1 and 4.");
                choice = 0; // if value is outside the desired range force the loop
            }

            switch (choice) {
                case 1: // Add Patron to Patron list, and Append patrons.txt
                    Patron newPatron = new Patron(patronList);
                    patronList.add(newPatron);
                    System.out.println(newPatron);
                    writeToFile(newPatron);
                    break;
                case 2: // Remove Patron from Patron list, and overwrite patrons.txt with modified list
                    System.out.print("Enter the Patron ID of the patron you would like to remove: ");
                    String removePatronID = input.nextLine().trim();

                    boolean removed = patronList.removeIf(p -> p.getUID().equalsIgnoreCase(removePatronID));

                    if (removed) {
                        System.out.printf("Patron [%s] has been removed from the list.%n", removePatronID);
                    } else {
                        System.out.println("Patron Not Found");
                    }

                    if (!patronList.isEmpty()) {
                        patronList.forEach(System.out::println); //method operator
                        savePatrons(patronList);
                    } else {
                        savePatrons(patronList);
                    }
                    break;
                case 3: // write patrons in patronList
                    Collections.sort(patronList);
                    if (!patronList.isEmpty()) {
                        patronList.forEach(System.out::println); //method operator
                    }
                    break;
                case 4: // Exit
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }

            if (choice != 4) {
                System.out.println("\nPress Enter to continue...");
                input.nextLine(); // this ensures the enduser has captured the output of their selected action before returning to the menu
            }

        } while (choice != 4);
    }

    // Takes in a generic object and determines how to serialize and write to the desired txt file
    public static <T> void writeToFile(T object) {
        if (object instanceof Patron) {
            try {
                Files.write(patronFile, (((Patron) object).serializePatron() + System.lineSeparator()).getBytes(),
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

    // This overwrites the existing txt file with the modified patronsList
    static void savePatrons(List<Patron> patrons) {
        try {
            Path dir = patronFile.getParent();
            if (dir != null && !Files.exists(dir)) {
                Files.createDirectories(dir);
            }

            ArrayList<String> lines = new ArrayList<>();

            for (Patron patron : patrons) {
                String line = patron.serializePatron();
                lines.add(line);
            }

            // overwrite the file with current list
            Files.write(patronFile, lines, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);

            System.out.printf("Saved %d patron(s) to %s.%n", patrons.size(), ".\\logs\\patrons.txt");
        } catch (IOException e) {
            System.out.println("Error saving patrons: " + e.getMessage());
        }
    }

    // This will parse the txt file and calls getPatron to undo the formating to convert back into and object
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

    // This will parse the txt file and calls getPatron to undo the formating to convert back into and object
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

    // This will parse the txt file and calls getPatron to undo the formating to convert back into and object
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


