package main.com.learntrack.studentmanagement.util;

public class IdGenerator {
    private static int studentID = 10000;
    private static int courseID = 10000;
    private static int enrollmentID = 10000;

    public static int getNextStudentID() {
        studentID++;
        return studentID;
    }

    public static int getNextCourseID() {
        courseID++;
        return courseID;
    }

    public static int getNextEnrollmentID() {
        enrollmentID++;
        return enrollmentID;
    }
}
