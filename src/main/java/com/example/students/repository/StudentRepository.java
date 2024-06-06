package com.example.students.repository;

// Importing required classes
import com.example.students.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository  extends JpaRepository<Student, Integer> {
    List<Student> findByStudentName(String studentName);

    List<Student> findByStudentSubject(String studentSubject);
}

