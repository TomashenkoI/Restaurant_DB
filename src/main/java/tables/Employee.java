package tables;

/**
 * Created by 7 on 12.08.2016.
 */
public class Employee extends Table{

    private int ID;
    private String lastName;
    private String firstName;
    private String dateOfBirth;
    private String phoneNumber;
    private int position;
    private double salary;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + ID +  "\t" +
                " lastName=" + "\t" + lastName +
                " firstName=" +  "\t" + firstName +
                " dateOfBirth=" +  "\t" + dateOfBirth +
                " phoneNumber=" +  "\t" + phoneNumber +
                " position=" +  "\t" + position + "\t" +
                " salary= " + salary +
                '}';
    }
}
