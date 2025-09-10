public class Address {

    private String unitNumber;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;

    public Address(String unit, String city, String street, String zip, String state, String country) {

        this.unitNumber = unit;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format("%s %s\n%s, %s %s\n", this.unitNumber, this.street, this.city, this.state, this.zip);
    };
}
