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

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("**************************************************************************");
        System.out.println("LIBRARY MANAGEMENT SYSTEM");

        int mainChoice;
        do {
            mainChoice = mainMenu(input);
            switch (mainChoice) {
                case 1:
                    runPatronMenu(input);
                    break;
                case 2:
                    runBookMenu(input);
                    break;
                case 3:
                    runTransactionMenu(input);
                    break;
                case 4:
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        } while (mainChoice != 4);

        input.close();
        System.out.println("Exit Code Given");
    }

    public static int mainMenu(Scanner input) {
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
            } catch (NumberFormatException e) {
                System.out.printf("%s is an Invalid input!\n", response);
            }

        } while (choice == 0);
        return choice;
    }

    public static int runPatronMenu(Scanner input) {
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
            } catch (NumberFormatException e) {
                System.out.printf("%s is an Invalid input!\n", response);
            }

        } while (choice == 0);
        return choice;
    }
}