package com.example.students.service;

import com.example.students.entity.Student;
import com.example.students.repository.StudentRepository;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class StudentServiceImpl implements StudentService {



    @Autowired
    private StudentRepository studentRepository;

@Override
    public Student saveStudent(Student student)
    {
        return studentRepository.save(student);
    }

@Override
    public List<Student> getAllStudent() {
        return (List<Student>)
                studentRepository.findAll();
    }
    @Override
    public List<Student> getStudentsByNameStartingWith(String studentName) {
        return studentRepository.findByStudentNameStartingWithIgnoreCase(studentName);
    }

    @Override
    public List<Student> getStudentsBySubjectStartingWith(String studentSubject) {
        return studentRepository.findByStudentSubjectStartingWithIgnoreCase(studentSubject);
    }

    @Override
    public List<Student> getStudentsByNameStartingWithAndSubjectStartingWith(String studentName, String studentSubject) {
        return studentRepository.findByStudentNameStartingWithIgnoreCaseAndStudentSubjectStartingWithIgnoreCase(studentName,studentSubject);
    }

    @Override
    public Student getStudentById(int studentId){

    return studentRepository.findById(studentId).get();
    }
    @Override
    public Student updateStudent(Student student, int studentId) {
        Student existingStudent = studentRepository.findById(studentId).orElse(null);
        if (existingStudent != null) {
            if (student.getStudentName() != null) {
                existingStudent.setStudentName(student.getStudentName());
            }
            if (student.getStudentClass() != null) {
                existingStudent.setStudentClass(student.getStudentClass());
            }
            if (student.getStudentSubject() != null) {
                existingStudent.setStudentSubject(student.getStudentSubject());
            }
            return studentRepository.save(existingStudent);
        } else {
            throw new IllegalArgumentException("Student not found");
        }
    }

    @Override
    public void deleteStudentById(int studentId) {
        studentRepository.deleteById(studentId);
    }


}
