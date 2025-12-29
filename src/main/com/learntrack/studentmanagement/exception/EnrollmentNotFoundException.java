package main.com.learntrack.studentmanagement.exception;

public class EnrollmentNotFoundException extends LearnTrackException {
    public EnrollmentNotFoundException(String message) {
        super(message);
    }

    public EnrollmentNotFoundException() {
        super("Enrollment not found");
    }

    public EnrollmentNotFoundException(int enrollmentId) {
        super("Enrollment with id " + enrollmentId + " not found");
    }
}
