import java.util.Scanner;

public class Person {

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

    @Override
    public String toString() {
        return String.format("First Name: %s\nLast Name: %s\nPhone: %s\nAddress: %s\n", this.firstName, this.lastName, this.phoneNumber, this.address);
    }

}