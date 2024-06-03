package com.example.students.controller;

import com.example.students.entity.Student;
import com.example.students.service.StudentService;

import java.util.List;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {


    @Autowired private StudentService studentService;


    @PostMapping("/students")
    public Student saveStudent(
            @Valid @RequestBody Student student)
    {
        return studentService.saveStudent(student);
    }


    @GetMapping("/students")
    public List<Student> getAllStudent()
    {
        return studentService.getAllStudent();
    }


    @PutMapping("/students/{id}")
    public Student
    updateStudent(@RequestBody Student student,
                     @PathVariable("id") int studentid)
    {
        return studentService.updateStudent(
                student, studentid);
    }
    @DeleteMapping("/students/{id}")
    public String deleteStudentById(@PathVariable("id")
                                       int studentId)
    {
        studentService.deleteStudentById(
                studentId);
        return "Deleted Successfully";
    }
}
