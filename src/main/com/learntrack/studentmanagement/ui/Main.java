package main.com.learntrack.studentmanagement.ui;

import main.com.learntrack.studentmanagement.entity.Course;
import main.com.learntrack.studentmanagement.entity.Enrollment;
import main.com.learntrack.studentmanagement.entity.Student;
import main.com.learntrack.studentmanagement.service.CourseService;
import main.com.learntrack.studentmanagement.service.EnrollmentService;
import main.com.learntrack.studentmanagement.service.StudentService;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static boolean exitFlag = false;

    public static Course getSelectedCourse(Scanner sc, String header, String footer,
                                           CourseService courseService, EnrollmentService enrollmentService) {

        List<Course> courses = courseService.getAllCourses();

        int index = 1;
        for(Course course : courses) {
            System.out.println(index++ + ". " + course.getCourseName());
        }

        System.out.println("Enter course number: ");
        int choice = sc.nextInt();

        if(choice < 1 || choice > courses.size()) {
            System.out.println("Invalid choice");
            displayCourseMenu(sc, header, footer, courseService, enrollmentService);
        }
        return courses.get(choice-1);
    }

    public static void displayEnrollemnts(Scanner sc, String header, String footer, CourseService courseService,
                                          StudentService studentService, EnrollmentService enrollmentService) {

        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        int index = 1;
        for (Enrollment enrollment : enrollments) {
            int studentId = enrollment.getStudentId();
            Student student = studentService.getStudentById(studentId);
            int courseId = enrollment.getCourseId();
            Course course = courseService.getCourseById(courseId);

            System.out.println(index++ + ". " + "Enrolled Student Name: " + student.getDisplayName() + '\n' +
                    "Enrolled Course Name: " + course.getCourseName() + '\n' +
                    "Enrollment Date: " + enrollment.getEnrolmentDate() + '\n');
        }
    }

    public static Enrollment getSelectedEnrollment(Scanner sc, String header, String footer, CourseService courseService,
                                                   StudentService studentService, EnrollmentService enrollmentService) {
        displayEnrollemnts(sc, header, footer, courseService, studentService, enrollmentService);
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        System.out.println("Enter Enrollment number: ");
        int enrollmentNumber = sc.nextInt();

        if(enrollmentNumber < 1 || enrollmentNumber > enrollments.size()) {
            System.out.println("Invalid enrollment number");
            displayEnrollmentMenu(sc, header, footer, courseService, studentService, enrollmentService);
        }
        return enrollments.get(enrollmentNumber-1);
    }

    public static void displayCourseMenu(Scanner sc, String header, String footer,
                                         CourseService courseService, EnrollmentService enrollmentService) {
        try {
            System.out.println(header);
            System.out.println(
                    """
                            || Course Menu ||
                            
                            Press 1 To add new Course
                            \
                            Press 2 To remove course
                            \
                            Press 3 To view course details
                            \
                            Press 4 To update course details
                            \
                            Press 5 To view all courses available
                            \
                            Press 6 To go back previous menu
                            \
                            Press any other key to exit
                            """
            );
            System.out.println(footer);

            int input = sc.nextInt();
            if (input == 1) {
                System.out.println("Enter course name: ");
                String courseName = sc.next();

                if (courseName.isBlank()) {
                    System.out.println("Name field cannot be blank");
                    return;
                }

                System.out.println("Enter course description: ");
                String courseDescription = sc.next();
                sc.nextLine();
                System.out.println("Enter course duration in weeks: ");
                int courseDuration = sc.nextInt();

                courseService.newCourse(courseName, courseDescription, courseDuration);
                System.out.println("New Course has been added successfully.\n");
            } else if (input == 2) {
                System.out.println("Select course from below options to remove: ");

                Course selectedCourse = getSelectedCourse(sc, header, footer, courseService, enrollmentService);
                List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
                if(!enrollments.isEmpty()) {
                    for (Enrollment enrollment : enrollments) {
                        if (enrollment.getCourseId() == selectedCourse.getId()) {
                            System.out.println("Student already enrolled with course Id: " + enrollment.getCourseId());
                            return;
                        }
                    }
                }
                courseService.removeCourse(selectedCourse, enrollments);

                System.out.println("Course has been removed successfully.\n");
            } else if (input == 3) {
                if(courseService.isCoursesEmpty()) {
                    System.out.println("There are no courses available");
                    return;
                }
                System.out.println("Select course from below options: ");
                Course selectedCourse = getSelectedCourse(sc, header, footer, courseService, enrollmentService);

                System.out.println("Course name: " + selectedCourse.getCourseName() + '\n' +
                        "Course Description: " + selectedCourse.getDescription() + '\n' +
                        "Course Duration in Weeks: " + selectedCourse.getDurationInWeeks() + '\n');

                System.out.println("Course details displayed successfully.\n");
            } else if (input == 4) {
                if (courseService.isCoursesEmpty()) {
                    System.out.println("There are no courses available");
                    return;
                }
                System.out.println("Select course from below options to update: ");
                List<Course> courses = courseService.getAllCourses();
                Course selectedCourse = getSelectedCourse(sc, header, footer, courseService, enrollmentService);

                System.out.println(
                        """
                                || Changing Course Details ||
                                
                                Press 1. To change Course name
                                \
                                Press 2. To change Course description
                                \
                                Press 3. To change Course duration in weeks"
                                \
                                Press 4. To exit :0
                                """
                );
                int choice = sc.nextInt();
                if (choice == 1) {
                    System.out.println("Enter course name: ");
                    String courseName = sc.next();
                    selectedCourse.setCourseName(courseName);
                } else if (choice == 2) {
                    System.out.println("Enter course description: ");
                    String courseDescription = sc.next();
                    selectedCourse.setDescription(courseDescription);
                } else if (choice == 3) {
                    System.out.println("Enter course duration in weeks: ");
                    int courseDuration = sc.nextInt();
                    selectedCourse.setDurationInWeeks(courseDuration);
                } else if (choice == 4) {
                    return;
                } else {
                    System.out.println("Invalid choice");
                    return;
                }

                courseService.updateCourse(selectedCourse);
                System.out.println("Course has been updated successfully.\n");
            } else if (input == 5) {
                if (courseService.isCoursesEmpty()) {
                    System.out.println("There are no courses available");
                    return;
                }

                List<Course> courses = courseService.getAllCourses();

                int index = 1;
                for (Course course : courses) {
                    System.out.println(index++ + ". " + "Course Name: " + course.getCourseName() + '\n' +
                            "Course Description: " + course.getDescription() + '\n' +
                            "Course Duration In Weeks: " + course.getDurationInWeeks());
                }
                System.out.println("List of Course displayed successfully.\n");
            } else if (input == 6) {
                return;
            } else {
                exitFlag = true;
                return;
            }
            displayCourseMenu(sc, header, footer, courseService, enrollmentService);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void displayEnrollmentMenu(Scanner sc, String header, String footer, CourseService courseService,
                                             StudentService studentService, EnrollmentService enrollmentService) {
        try {
            System.out.println(header);
            System.out.println(
                    """
                            || Enrollment Menu ||
                            
                            Press 1. To add new Enrollment
                            \
                            Press 2. To remove Enrollments
                            \
                            Press 3. To view enrollment details
                            \
                            Press 4. To update Student details
                            \
                            Press 5. To go back previous menu
                            \
                            Press any other key to exit :0
                            """
            );
            System.out.println(footer);
            int choice = sc.nextInt();
            if (choice == 1) {
                System.out.println("Select course from below options to add: ");
                Course selectedCourse = getSelectedCourse(sc, header, footer, courseService, enrollmentService);

                System.out.println("Enter Student First Name: ");
                String studentName = sc.next();
                if (studentName.isBlank()) {
                    System.out.println("Student name cannot be blank");
                    return;
                }
                System.out.println("Enter Student Last Name: ");
                String studentLastName = sc.next();
                System.out.println("Enter Student Email: ");
                String studentEmail = sc.next();
                if (studentEmail.isBlank()) {
                    System.out.println("Student email cannot be blank");
                    return;
                }
                System.out.println("Enter Student Batch: ");
                String studentBatch = sc.next();

                Student newStudent = studentService.newStudent(studentName, studentLastName, studentEmail, studentBatch);
                enrollmentService.newEnrollment(newStudent, selectedCourse);
                System.out.println("Student has been enrolled successfully.\n");
            } else if (choice == 2) {
                System.out.println("Select from below enrollments to remove");
                Enrollment selectedEnrollment = getSelectedEnrollment(sc, header, footer, courseService, studentService, enrollmentService);
                enrollmentService.removeEnrollment(selectedEnrollment);
            } else if (choice == 3) {
                if (enrollmentService.isEnrollmentsEmpty()) {
                    System.out.println("There are no enrollments available");
                    return;
                }
                displayEnrollemnts(sc, header, footer, courseService, studentService, enrollmentService);
                System.out.println("Enrollment has been viewed successfully.\n");
            } else if (choice == 4) {
                if(studentService.isStudentsEmpty()){
                    System.out.println("There are no students available");
                    return;
                }

                sc.nextLine();
                System.out.println("Enter first name of student you want to change: ");
                String firstName = sc.next();
                List<Student> students = studentService.getAllStudents();
                for (Student student : students) {
                    if (!student.getFirstName().equals(firstName)) {
                        System.out.println("Student name does not match");
                        return;
                    }
                }
                Student student = studentService.getStudentByName(firstName);

                System.out.println(
                        """
                                || Changing Student Details ||
                                
                                Press 1. To change Student First Name
                                \
                                Press 2. To change Student Last Name
                                \
                                Press 3. To change Student Email
                                \
                                Press 4. To change Student Batch
                                \
                                Press 5. To exit :0
                                """
                );
                int input = sc.nextInt();

                if (input == 1) {
                    System.out.println("Enter Student First Name: ");
                    String studentFirstName = sc.next();
                    student.setFirstName(studentFirstName);
                } else if (input == 2) {
                    System.out.println("Enter Student Last Name: ");
                    String lastName = sc.next();
                    student.setLastName(lastName);
                } else if (input == 3) {
                    System.out.println("Enter Student Email: ");
                    String email = sc.next();
                    student.setEmail(email);
                } else if (input == 4) {
                    System.out.println("Enter Student Batch: ");
                    String batch = sc.next();
                    student.setBatch(batch);
                }
                else if(input == 5){
                    return;
                }
                else {
                    System.out.println("Invalid choice");
                    return;
                }

                studentService.updateStudent(student);
                System.out.println("Student has been updated successfully.\n");
            } else {
                exitFlag = true;
                return;
            }
            displayEnrollmentMenu(sc, header, footer, courseService, studentService, enrollmentService);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String header =
                """
                    ===========================================
                    Welcome to Learn Track Management System !
                    ===========================================""";
        String footer = "Choose your choice: ";

        CourseService courseService = new CourseService();
        StudentService studentService = new StudentService();
        EnrollmentService enrollmentService = new EnrollmentService();

        while(true){
            System.out.println(header);
            System.out.println(
                    """
                            
                            Press 1. To go Course menu
                            \
                            Press 2. To go Enrollment menu
                            \
                            Press any other key to exit :0
                            """
            );
            System.out.println(footer);
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            switch (input){
                case 1:
                    displayCourseMenu(scanner, header, footer, courseService, enrollmentService);
                    break;
                case 2:
                    displayEnrollmentMenu(scanner, header, footer, courseService, studentService, enrollmentService);
                    break;
                default:
                    exitFlag = true;
                    break;
            }
            if(exitFlag){
                System.out.println("Thank you for using Learn Track Management System :)");
                exitFlag = false;
                break;
            }
        }
    }
}
