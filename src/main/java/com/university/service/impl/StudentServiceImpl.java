package com.university.service.impl;

import com.university.model.Student;
import com.university.repository.abstracts.StudentDao;
import com.university.service.abstracts.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAll();
    }

    @Override
    public Student getByName(String name) {
        return studentDao.getByName(name);
    }

    @Override
    public void add(Student student) {
        studentDao.persist(student);
    }

    @Override
    public void delete(Student student) {
        studentDao.delete(student);
    }
}
