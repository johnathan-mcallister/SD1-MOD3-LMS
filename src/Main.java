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

        System.out.printf("Hello What is your name?\n");
        String name = input.nextLine();
        System.out.printf("Welcome %s!", name);

    }

    public static int menu(Scanner input) {
        System.out.printf("MAIN MENU\n");
        int choice = 0;
        while(choice < 1 || choice > 3) {
            System.out.printf("Choose a number between 1 and 3: \n");
            choice = input.nextLine();

        }
        return choice;
    }
}