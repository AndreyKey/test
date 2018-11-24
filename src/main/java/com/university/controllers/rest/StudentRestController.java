package com.university.controllers.rest;

import com.university.model.Student;
import com.university.service.abstracts.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/student")
public class StudentRestController {
    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/get-all-students")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.status(200).body(students);
    }
}
