package main.com.learntrack.studentmanagement.service;

import main.com.learntrack.studentmanagement.entity.Student;
import main.com.learntrack.studentmanagement.exception.InvalidInputException;
import main.com.learntrack.studentmanagement.exception.StudentNotFoundException;
import main.com.learntrack.studentmanagement.util.IdGenerator;
import main.com.learntrack.studentmanagement.util.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private final List<Student> students = new ArrayList<>();

    public Student newStudent(String firstName, String lastName, String email, String batch){
        boolean requiredFields = InputValidator.validateNewStudent(firstName, email);
        if(!requiredFields){
            throw new InvalidInputException("First name and email is required");
        }
        int id = IdGenerator.getNextStudentID();
        Student student = new Student(id, firstName,lastName, email, batch);
        students.add(student);
        return student;
    }

    public void removeStudent(Student student){
        boolean validateStudent = InputValidator.validateStudent(student);
        if(!validateStudent){
            throw new InvalidInputException("Student is invalid");
        }
        boolean removed = students.removeIf(s -> s.getId() == student.getId());
        if(!removed){
            throw new StudentNotFoundException(student.getId());
        }
    }

    public Student getStudentById(int StudentId){
        return students.stream()
                .filter(s -> s.getId() == StudentId)
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException(StudentId));
    }

    public Student getStudentByName(String firstName){
        return students.stream()
                .filter(s -> s.getFirstName().equals(firstName))
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException("Student not found with Name " + firstName));
    }

    public void updateStudent(Student updatedStudent){
        boolean validateStudent = InputValidator.validateStudent(updatedStudent);
        if(!validateStudent){
            throw new  InvalidInputException("Student is invalid");
        }
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).getId() == updatedStudent.getId()){
                students.set(i, updatedStudent);
                return;
            }
        }
        throw new StudentNotFoundException(updatedStudent.getId());
    }

    public List<Student> getAllStudents(){
        if(students.isEmpty()){
            throw new StudentNotFoundException();
        }
        return new  ArrayList<>(students);
    }

    public boolean isStudentsEmpty(){
        return students.isEmpty();
    }
}
