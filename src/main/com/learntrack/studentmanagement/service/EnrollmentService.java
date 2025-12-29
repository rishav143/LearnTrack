package main.com.learntrack.studentmanagement.service;

import main.com.learntrack.studentmanagement.entity.Course;
import main.com.learntrack.studentmanagement.entity.Student;
import main.com.learntrack.studentmanagement.exception.CourseNotFoundException;
import main.com.learntrack.studentmanagement.exception.EnrollmentNotFoundException;
import main.com.learntrack.studentmanagement.exception.StudentNotFoundException;
import main.com.learntrack.studentmanagement.service.CourseService;
import main.com.learntrack.studentmanagement.entity.Enrollment;
import main.com.learntrack.studentmanagement.util.IdGenerator;
import main.com.learntrack.studentmanagement.util.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {
    private final List<Enrollment> enrollments = new ArrayList<>();

    public void newEnrollment(Student student, Course course) {
        boolean validateStudent = InputValidator.validateStudent(student);
        if (!validateStudent) {
            throw new StudentNotFoundException();
        }
        boolean validateCourse = InputValidator.validateCourse(course);
        if (!validateCourse) {
            throw new CourseNotFoundException();
        }
        int newEnrollmentId = IdGenerator.getNextEnrollmentID();
        Enrollment enrollment = new Enrollment(newEnrollmentId, student.getId(),  course.getId());
        enrollments.add(enrollment);
    }

    public void removeEnrollment(Enrollment enrollment) {
        boolean validateEnrollment = InputValidator.validateEnrollment(enrollment);
        if (!validateEnrollment) {
            throw new EnrollmentNotFoundException();
        }
        boolean removed = enrollments.removeIf(e -> e.getId() == enrollment.getId());
        if (!removed) {
            throw new EnrollmentNotFoundException(enrollment.getId());
        }
    }

    public void removeEnrollment(Course course) {
        boolean validateCourse = InputValidator.validateCourse(course);
        if (!validateCourse) {
            throw new CourseNotFoundException();
        }
        boolean removed = enrollments.removeIf(e -> e.getCourseId() == course.getId());
        if (!removed) {
            throw new CourseNotFoundException(course.getId());
        }
    }

    public void updateEnrollment(Enrollment enrollment) {
        boolean validateEnrollment = InputValidator.validateEnrollment(enrollment);
        if (!validateEnrollment) {
            throw new EnrollmentNotFoundException();
        }
        for(int i = 0; i < enrollments.size(); i++) {
            if(enrollments.get(i).getId() == enrollment.getId()) {
                enrollments.set(i, enrollment);
                return;
            }
        }
        throw new EnrollmentNotFoundException(enrollment.getId());
    }

    public Enrollment getEnrollmentById(int enrollmentId) {
        return enrollments.stream()
                .filter(e -> e.getId() == enrollmentId)
                .findFirst()
                .orElseThrow(() -> new EnrollmentNotFoundException(enrollmentId));
    }

    public List<Enrollment> getAllEnrollments() {
        if (enrollments.isEmpty()) {
            throw new EnrollmentNotFoundException("No enrollments found");
        }
        return enrollments;
    }

    public boolean isEnrollmentsEmpty() {
        return enrollments.isEmpty();
    }
}
