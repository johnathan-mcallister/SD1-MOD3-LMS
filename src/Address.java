import java.util.Scanner;

public class Address {

    private String unitNumber;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    private static final Scanner input = new Scanner(System.in);

    public Address() {
        setUnitNumber();
        setStreet();
        setCity();
        setState();
        setZip();
        setCountry();
    }

    public Address(String unitNumber, String street, String city, String state, String zip, String country) {
        this.unitNumber = unitNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public String getUnitNumber() {
        return this.unitNumber;
    }

    public void setUnitNumber() {
        System.out.println("Unit Number:");
        this.unitNumber = input.nextLine();
    }

    public String getCity() {
        return city;
    }

    public void setCity() {
        System.out.println("City:");
        this.city = input.nextLine();
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet() {
        System.out.println("Street:");
        this.street = input.nextLine();
    }

    public String getZip() {
        return this.zip;
    }

    public void setZip() {
        System.out.println("Zip Code:");
        this.zip = input.nextLine();
    }

    public String getState() {
        return this.state;
    }

    public void setState() {
        System.out.println("State:");
        this.state = input.nextLine();
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry() {
        System.out.println("Country:");
        this.country = input.nextLine();
    }

    @Override
    public String toString() {
        return String.format("ADDRESS:\n    %s %s\n    %s, %s %s", this.unitNumber, this.street, this.city, this.state.toUpperCase(), this.zip);
    };
}
