package main.com.learntrack.studentmanagement.service;

import main.com.learntrack.studentmanagement.entity.Course;
import main.com.learntrack.studentmanagement.entity.Enrollment;
import main.com.learntrack.studentmanagement.exception.InvalidInputException;
import main.com.learntrack.studentmanagement.exception.CourseNotFoundException;
import main.com.learntrack.studentmanagement.util.IdGenerator;
import main.com.learntrack.studentmanagement.util.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private final List<Course> courses = new ArrayList<>();

    public void newCourse(String courseName, String description, int durationInWeeks) {
        boolean checkRequiredFields = InputValidator.validateCourseName(courseName);
        if(!checkRequiredFields) {
            throw new InvalidInputException("Course name is invalid.");
        }
        int newCourseId = IdGenerator.getNextCourseID();
        Course newCourse = new Course(newCourseId, courseName, description, durationInWeeks);
        courses.add(newCourse);
    }

    public void removeCourse(Course course, List<Enrollment> enrollments) {
        boolean validateCourse =  InputValidator.validateCourse(course);
        if(!validateCourse) {
            throw new CourseNotFoundException();
        }
        if(!enrollments.isEmpty()) {
            for (Enrollment enrollment : enrollments) {
                if (enrollment.getCourseId() == course.getId()) {
                    throw new InvalidInputException("Students already enrolled with id " + course.getId());
                }
            }
        }
        boolean removed = courses.removeIf(c -> c.getId() == course.getId());
        if (!removed) {
            throw new CourseNotFoundException(course.getId());
        }
    }

    public Course getCourseById(int courseId) {
        return courses.stream()
                .filter(c -> c.getId() == courseId)
                .findFirst()
                .orElseThrow(() -> new CourseNotFoundException(courseId));
    }

    public void updateCourse(Course updatedCourse) {
        boolean validateCourse =  InputValidator.validateCourse(updatedCourse);
        if(!validateCourse) {
            throw new CourseNotFoundException();
        }
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == updatedCourse.getId()) {
                courses.set(i, updatedCourse);
                return;
            }
        }
        throw new CourseNotFoundException(updatedCourse.getId());
    }

    public List<Course> getAllCourses() {
        if(courses.isEmpty()) {
            throw new CourseNotFoundException("No courses found.");
        }
        return new ArrayList<>(courses);
    }

    public boolean isCoursesEmpty() {
        return courses.isEmpty();
    }
}
