package main.com.learntrack.studentmanagement.entity;

import java.time.LocalDate;

public class Enrollment {

    private final int id;
    private int studentId;
    private int courseId;
    private LocalDate enrollmentDate;
    private Status status;

    public enum Status {
        ACTIVE, COMPLETED, CANCELLED
    }

    public Enrollment(int id, int studentId, int courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.status = Status.ACTIVE;
        this.enrollmentDate = LocalDate.now();
    }

    //getters
    public int getId() {
        return id;
    }

    public LocalDate getEnrolmentDate() {
        return enrollmentDate;
    }

    public int getStudentId() {
        return studentId;
    }
    public int getCourseId() {
        return courseId;
    }

    //setters
    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public void complete() {
        this.status = Status.COMPLETED;
    }

    public void cancel() {
        this.status = Status.CANCELLED;
    }
}
