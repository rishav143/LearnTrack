package main.com.learntrack.studentmanagement.util;

import main.com.learntrack.studentmanagement.entity.Course;
import main.com.learntrack.studentmanagement.entity.Enrollment;
import main.com.learntrack.studentmanagement.entity.Student;

public class InputValidator {
    public static boolean validateStudent(Student student) {
        return student != null;
    }

    public static boolean validateNewStudent(String firstName, String email) {
        return firstName != null
                && !firstName.isBlank()
                && email != null
                && !email.isBlank();
    }

    public static boolean validateCourse(Course course) {
        return course != null;
    }

    public static boolean validateCourseName(String courseName) {
        return courseName != null && !courseName.isBlank();
    }

    public static boolean validateNewEnrollment(Student student, Course course) {
        return InputValidator.validateStudent(student) &&
                InputValidator.validateCourse(course);
    }

    public static boolean validateEnrollment(Enrollment enrollment) {
        return enrollment != null;
    }
}