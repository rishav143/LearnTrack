package main.com.learntrack.studentmanagement.entity;

public class Trainer extends Person {
    private String batch;
    private boolean active;

    public Trainer(int id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email);
        this.batch = batch;
    }
}
