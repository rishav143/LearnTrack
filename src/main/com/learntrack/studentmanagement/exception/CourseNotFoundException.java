package main.com.learntrack.studentmanagement.exception;

public class CourseNotFoundException extends LearnTrackException {

    public CourseNotFoundException(String message) {
        super(message);
    }

    public CourseNotFoundException() {
        super("Course not found");
    }

    public CourseNotFoundException(int CourseId) {
        super("Course not found with id : " + CourseId);
    }
}
