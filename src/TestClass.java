import java.util.ArrayList;
import java.util.Scanner;

public class TestClass {

    public static final Scanner input = new Scanner(System.in);
    public static ArrayList<Patron> patronList = new ArrayList<Patron>();

    public static void main(String[] args) {
        runPatronMenu();
    }

    public static void runPatronMenu() {
        //System.out.println("\033[h\033[2J");
        //System.out.flush();
        System.out.println("*************************************");
        System.out.println("************ [ PATRONS ] ************");
        System.out.println("*************************************");
        System.out.println("1. Add Patron");
        System.out.println("2. Remove Patron");
        System.out.println("3. Retrieve List of Patrons");
        System.out.println("4. Exit");
        System.out.println("*************************************");
        int choice = 0;
        do {
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
                    System.out.println("Remove Patron");
                    break;
                case 3:
                    System.out.println("List All Patrons");
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
