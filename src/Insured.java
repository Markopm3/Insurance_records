public class Insured {
    private String firstName;
    private String lastName;
    private int age;
    private String telephoneNumber;

    public Insured(String firstName, String lastName, String telephoneNumber, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.telephoneNumber = telephoneNumber;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public String toString() {
        return String.format("%s\t   %s\t       %d\t   %s\n", getFirstName(), getLastName(), getAge(), getTelephoneNumber());
    }
}
