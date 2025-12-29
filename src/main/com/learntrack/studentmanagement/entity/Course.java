package main.com.learntrack.studentmanagement.entity;

public class Course {

    private final int id;              // immutable identity
    private String courseName;
    private String description;
    private int durationInWeeks;
    private boolean active;

    public Course(int id, String courseName) {
        this.id = id;
        this.courseName = courseName;
        this.active = true; // default
    }
    public Course(int id, String courseName, String description, int durationInWeeks) {
        this(id, courseName);
        this.description = description;
        this.durationInWeeks = durationInWeeks;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDescription() {
        return description;
    }

    public int getDurationInWeeks() {
        return durationInWeeks;
    }

    public boolean isActive() {
        return active;
    }

    // Setters
    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDurationInWeeks(int durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }

    public void deactivate() {
        this.active = false;
    }
}
