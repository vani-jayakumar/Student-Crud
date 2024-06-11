package com.example.students.controller;

import com.example.students.entity.Student;
import com.example.students.service.StudentService;

import java.io.IOException;
import java.util.List;

import com.example.students.util.ExcelGenerator;
import jakarta.servlet.http.HttpServletResponse;
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

        public List<Student> getAllStudent(
                @RequestParam(value = "name", required = false) String studentName,
                @RequestParam(value = "subject", required = false) String studentSubject) {
        if(studentName != null &&  studentSubject != null){
            return studentService.getStudentsByNameStartingWithAndSubjectStartingWith(studentName,studentSubject);
        }else if(studentName != null) {
            return studentService.getStudentsByNameStartingWith(studentName);
        } else if (studentSubject != null) {
            return studentService.getStudentsBySubjectStartingWith(studentSubject);
        }else{
                return studentService.getAllStudent();
            }
        }


    @GetMapping("/students/{id}")
    public Student
    getStudentById(@RequestBody Student student,
                   @PathVariable("id") int studentId){
        return studentService.getStudentById(studentId);
    }

    @PutMapping("/students/{id}")
    public Student
    updateStudent(@RequestBody Student student,
                     @PathVariable("id") int studentId)
    {
        return studentService.updateStudent(
                student, studentId);
    }
    @DeleteMapping("/students/{id}")
    public String deleteStudentById(@PathVariable("id")
                                       int studentId)
    {
        studentService.deleteStudentById(
                studentId);
        return "Deleted Successfully";
    }
    @GetMapping("/students/downloadAsExcel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=student" + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List <Student> listOfStudents = studentService.getAllStudent();
        ExcelGenerator generator = new ExcelGenerator(listOfStudents);
        generator.generateExcelFile(response);
    }
}
