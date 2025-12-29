package main.com.learntrack.studentmanagement.entity;

public abstract class Person {

    private final int id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean active = false;

    public Person(int id, String firstName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
    }

    public Person(int id, String firstName, String lastName, String email) {
        this(id, firstName, email);
        this.lastName = lastName;
        this.active = true;
    }

    //getters
    public String getDisplayName() {
        return firstName + " " + lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    //setters
    public void deactivate() {
        this.active = false;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
