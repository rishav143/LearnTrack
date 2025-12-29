package main.com.learntrack.studentmanagement.exception;

public class StudentNotFoundException extends LearnTrackException {

    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException() {
        super("Student Not Found");
    }

    public StudentNotFoundException(int StudentId) {
        super("Student not found with id : " + StudentId);
    }

}
