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
    public Student getStudentById(int studentId){
        return studentRepository.findById(studentId).get();
    }
@Override
    public Student updateStudent(Student student, int studentId) {
       return studentRepository.save(student);
    }
@Override
    public void deleteStudentById(int studentId) {
        studentRepository.deleteById(studentId);
    }


}
