# CEN 3024C - Software Development 1
* **Professor:** Dr. Lisa Macon
* **Course ID:** 202610
* **CRN:** 14877
* **Semester:** Fall 2025
***
# Library Management System (Module 3 Assignment)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white) ![YouTube](https://img.shields.io/badge/YouTube-%23FF0000.svg?style=for-the-badge&logo=YouTube&logoColor=white)

## Project Description
The purpose of this Software Development Plan is to outline the structure, features, 
and operational requirements of the Library Management System (LMS). This system is 
designed to provide a simple, console-based solution for managing patrons, books, and 
transactions such as check-ins, checkouts, and fine tracking. By defining requirements, 
constraints, implementation strategies, and testing approaches, this plan ensures that 
development proceeds in a clear and organized manner.

The system is intended to be lightweight and accessible, relying on console 
interactions rather than graphical interfaces or database integrations. This keeps the focus 
on core functionality such as patron management, book tracking, and enforcing library 
policies like overdue fines and checkout limits.

This plan also documents how the system will be implemented step by step, how 
risks and constraints will be addressed, and how testing will validate functionality. 
Together, these elements form a complete roadmap for building a reliable, maintainable 
system that meets user expectations while adhering to project constraints.

## System Requirements
### Menu Structure
* The system must provide a console-based interface with a main menu.
* The main menu must include sub-menus for:
    * Patrons
    * Books
    * Transactions
* The system must allow users to return to the main menu from any sub-menu.

### Patron Management
* The system must allow adding patrons manually or by importing from a text file 
(delimited by “-”).
* The system must allow removal of patrons by entering their UID.
* The system must display a patron list in alphabetical order by last name.
* The system must allow viewing a patron’s checkout list by entering their UID.
* Patron records must include: name, address, phone number, overdue fine balance, 
and a list of checked-out books (can be empty array).
* Patron IDs must be unique.

### Book Management
* The system must allow adding books manually or by importing from a text file
(delimited by “-”).
* The system must allow removing books by UID, unless the book is currently checked 
out.
* The system must display a book list sorted alphabetically by the author’s last name.
* The system must support book search with the following options:
    * Search all books (returns entire book list).
    * Search by genre.
    * Search by title.
    * Search by author.
    * Search by ISBN.
    * Search by UID.
* Search results and book lists must display: title, author, genre, ISBN, quantity 
available, and quantity checked out.
* If a search returns a single book title, the system will display the full details for each 
copy.
* each book will have a Unique book ID (distinct from ISBN) generated from the following structure:
    * Genre identifier (3 characters), First 3 characters from Author’s last name,ISBN, 2 digit copy id.
    * e.g. Title: Moby Dick, Author: Herman Melville, Genre: Fiction, Book# in Author’s body of work: 6, Library owned copy: 2
    * The above book would have the following UID: FIC-MEL-{ISBN}-002

### Check-out status.
* Patron ID, check-out date, and due date if the copy is checked out.
* Every copy of a book must have a unique book ID in addition to the ISBN to differentiate individual copies.

### Transactions
* The system must allow checking out books by patron ID or by searching for a patron 
by name.
* The system must allow selecting books to check out by UID.
* The system must support multiple books in a single checkout transaction.
* The system must block checkout if a patron’s fines exceed $250.
* The system must allow checkout with a warning if fines exist but are under the limit.
* The system must enforce a maximum of 25 books checked out per patron.
* The system must allow checking in books.
* During check-in, the system must prompt whether the patron has satisfied their balance in full, partially (and by how much), or not at all (if applicable).
* The system must display confirmation messages for all checkout and check-in actions.

### Fines & Due Dates
* The system must calculate overdue fines at $5 per overdue book.
* The system must set due dates at two weeks from the checkout date.
* If the due date falls on a Sunday, it must be extended by one day.
* The system must automatically update overdue fines when books are checked in.

### General Behavior
* The system must display confirmation prompts before permanently removing patrons or books.
* The system must refresh patron and book lists after any updates.
* The system must handle empty lists gracefully (e.g., no patrons or no books).

### Constraints
* The system must run in a console-only environment (no GUI).
* The system must not use a relational database (data stored locally).
* Patron IDs must be unique.
* Each copy of a book must have a unique book ID (ISBNs may repeat across copies of the same title).
