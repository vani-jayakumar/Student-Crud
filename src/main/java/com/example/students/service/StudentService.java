package com.example.students.service;

import com.example.students.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {

    // Save operation
    Student saveStudent(Student student);

    // Read operation
    List<Student> getAllStudent();
    List<Student> getStudentsByNameStartingWith(String studentName);
    List<Student> getStudentsBySubjectStartingWith(String studentSubject);
    List<Student> getStudentsByNameStartingWithAndSubjectStartingWith(String studentName, String studentSubject);
    Student getStudentById(int studentId);

    // Update operation
    Student updateStudent(Student student,
                                int studentId);



    // Delete operation
    void deleteStudentById(int studentId);



}
