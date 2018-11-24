package com.university.service.abstracts;

import com.university.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student getByName(String name);

    void add(Student student);

    void delete(Student resStudent);
}
