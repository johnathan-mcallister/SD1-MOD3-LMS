public class Person {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address address;

    public Person(String fName, String lName, String phone, Address address) {
        this.firstName = fName;
        this.lastName = lName;
        this.phoneNumber = phone;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("First Name: %s\nLast Name: %s\nPhone: %s\nAddress: %s\n", this.firstName, this.lastName, this.phoneNumber, this.address);
    }

}