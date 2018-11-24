package com.university.repository.impl;

import com.university.model.Student;
import com.university.repository.abstracts.GroupDao;
import com.university.repository.abstracts.StudentDao;
import com.university.service.abstracts.GroupService;
import com.university.service.abstracts.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupDaoImplTest {
    @Autowired
    private StudentService studentService;
    @Autowired
    private GroupService groupService;


    @Test
    public void addStudentsToGroup() {
        Student student = new Student("Hey-ho",3343);
        studentService.add(student);
        groupService.addStudentsToGroup("test1", student.getName());
        List<Student> studentList = groupService.getByName("test1").getStudents();
        assertThat(studentList.size(), is(3));
    }
}