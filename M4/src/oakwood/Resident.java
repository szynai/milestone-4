
package oakwood;

public class Resident extends Person {
    private String address;
    private String email;

    public Resident(String name, String contact, String address, String email) {
        super(name, contact);
        this.address = address;
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}

