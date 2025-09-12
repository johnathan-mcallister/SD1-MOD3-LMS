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
            System.out.println("1. Import Patron");
            System.out.println("1. Add Patron");
            System.out.println("2. Remove Patron");
            System.out.println("3. Retrieve List of Patrons");
            System.out.println("4. Exit");
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
                    Patron newPatron = new Patron(patronList);
                    patronList.add(newPatron);
                    System.out.println(newPatron);
                    break;
                case 2:
                    System.out.print("Enter the Patron ID of the patron you would like to remove: ");
                    String removePatronID = input.nextLine().trim();

                    boolean removed = false;
                    for (int i = 0; i < patronList.size(); i++) {
                        Patron p = patronList.get(i);
                        if (p.getUID().equalsIgnoreCase(removePatronID)) {
                            patronList.remove(i);
                            removed = true;
                            break; // stop after removing the first match
                        }
                    }

                    if (removed) {
                        System.out.printf("Patron [%s] has been removed from the list.%n", removePatronID);
                    } else {
                        System.out.println("Patron Not Found");
                    }

                    if (!patronList.isEmpty()) {
                        patronList.forEach(System.out::println);
                    }
                    break;
                case 3:
                    Collections.sort(patronList);

                    if (!patronList.isEmpty()) {
                        for (Patron patron : patronList) {
                            System.out.println(patron);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        } while (choice != 4);
    }

}
