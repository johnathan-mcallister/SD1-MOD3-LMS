import java.util.Scanner;

public abstract class Person implements Comparable<Person> {

    public String firstName;
    public String lastName;
    public String phoneNumber;
    public Address address;
    public final Scanner input = new Scanner(System.in);

    public Person() {
        setFirstName();
        setLastName();
        setPhoneNumber();
        setAddress();
    }

    public Person(String type) {
        if (type.equalsIgnoreCase("author")) {
            setFirstName();
            setLastName();
        } else {
            setFirstName();
            setLastName();
            setPhoneNumber();
            setAddress();
        }
    }

    public Person(String fName, String lName, String phoneNumber, String unitNum, String street, String city, String state, String zipCode, String country){
        this.firstName = fName;
        this.lastName = lName;
        this.phoneNumber = phoneNumber;
        this.address = new Address(unitNum, street, city, state, zipCode, country);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName() {
        System.out.println("First Name:");
        this.firstName = input.nextLine();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName() {
        System.out.println("Last Name:");
        this.lastName = input.nextLine();;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber() {
        System.out.println("Phone Number:");
        this.phoneNumber = input.nextLine();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress() {
        this.address = new Address();
    }

    public int compareTo(Person other) {
        int last = this.lastName.compareToIgnoreCase(other.lastName);
        if (last != 0) return last;
        return this.firstName.compareToIgnoreCase(other.firstName);
    }

    @Override
    public String toString() {
        return String.format("First Name: %s\nLast Name: %s\nPhone: %s\nAddress: %s\n", this.firstName, this.lastName, this.phoneNumber, this.address);
    }

}