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

import java.util.Scanner;

public class TestClass2 {

    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("**************************************************************************");
        System.out.println("LIBRARY MANAGEMENT SYSTEM");

        int mainChoice;
        do {
            mainChoice = mainMenu();
            switch (mainChoice) {
                case 1:
                    runPatronMenu();
                    break;
                case 2:
                    runBookMenu();
                    break;
                case 3:
                    runTransactionMenu();
                    break;
                case 4:
                    System.out.println("Goodbye.");
                    System.out.println("**************************************************************************");
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        } while (mainChoice != 4);

        input.close();
        System.out.println("Exit Code Given");
    }

    public static int mainMenu(){
        //System.out.println("\033[h\033[2J");
        //System.out.flush();
        System.out.println("*************************************");
        System.out.println("*********** [ MAIN MENU ] ***********");
        System.out.println("*************************************");
        System.out.println("1. Patrons");
        System.out.println("2. Books");
        System.out.println("3. Transactions");
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
        } while (choice < 1 || choice > 4);
        return choice;
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
                    System.out.println("Add Patron");
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

    public static void runBookMenu() {
        //System.out.println("\033[h\033[2J");
        //System.out.flush();
        System.out.println("*************************************");
        System.out.println("************* [ BOOKS ] *************");
        System.out.println("*************************************");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Retrieve List of Books");
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
                    System.out.println("Add Book");
                    break;
                case 2:
                    System.out.println("Remove Book");
                    break;
                case 3:
                    System.out.println("List All Books");
                    break;
                case 4:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        } while (choice != 4);
    }

    public static void runTransactionMenu() {
        //System.out.println("\033[h\033[2J");
        //System.out.flush();
        System.out.println("*************************************");
        System.out.println("********* [ TRANSACTIONS ] **********");
        System.out.println("*************************************");
        System.out.println("1. Check-Out");
        System.out.println("2. Check-In");
        System.out.println("3. Pay Fines");
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
                    System.out.println("Check-Out");
                    break;
                case 2:
                    System.out.println("Check-In");
                    break;
                case 3:
                    System.out.println("Pay Fines");
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