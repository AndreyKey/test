package com.university.controllers.rest;

import com.university.model.Group;
import com.university.model.Student;
import com.university.service.abstracts.GroupService;
import com.university.service.abstracts.StudentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GroupRestControllerTest {
    @Autowired
    private GroupRestController controller;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GroupService groupService;
    @Autowired
    private StudentService studentService;
    private String name = "test25";


    @Test
    public void groupController() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void createNewGroup() throws Exception {
        mockMvc.perform(get("/rest/group/create-new-group/" + name))
                .andExpect(status().isOk());
        Group group = groupService.getByName(name);
        groupService.delete(group);
    }

    @Test
    public void getGroupByName() throws Exception {
        Group group = new Group(name);
        groupService.add(group);
        Group testGroup = groupService.getByName(name);
        String entityData = String.format("{\"id\":%s,\"name\":\"%s\",\"students\":null}", testGroup.getId(), testGroup.getName());
        mockMvc.perform(get("/rest/group/get-group-by-name/" + name))
                .andExpect(status().isOk())
                .andExpect(content().string(entityData));

        groupService.delete(testGroup);
    }

    @Test
    public void addStudentsToGroup() throws Exception {
        String studentName = "testStudent";
        Integer studentAge = 24;
        studentService.add(new Student(studentName, studentAge));
        groupService.add(new Group(name));
        String query = String.format("/rest/group/add-students-to-group/group/%s/student/%s", name, studentName);
        mockMvc.perform(get(query))
                .andExpect(status().isOk());

        Group testGroup = groupService.getByName(name);
        Student resStudent = testGroup.getStudents().get(0);
        groupService.delete(testGroup);
        studentService.delete(resStudent);
        Assert.assertThat(resStudent.getName(), is(studentName));
    }

    @Test
    public void getAllStudentFromGroup() throws Exception {
        Student student1 = new Student("testStudent1", 21);
        Student student2 = new Student("testStudent2", 22);
        Group testGroup = new Group(name);
        studentService.add(student1);
        studentService.add(student2);
        groupService.add(testGroup);
        groupService.addStudentsToGroup(testGroup.getName(), student1.getName());
        groupService.addStudentsToGroup(testGroup.getName(), student2.getName());

        List<Student> studentList = groupService.getByName(name).getStudents();
        Student resStudent1 = studentList.get(0);
        Student resStudent2 = studentList.get(1);

        String entityData2 = String.format("[{\"id\":%s,\"name\":\"%s\",\"age\":%s},{\"id\":%s,\"name\":\"%s\",\"age\":%s}]",
                resStudent1.getId(), resStudent1.getName(), resStudent1.getAge(), resStudent2.getId(), resStudent2.getName(), resStudent2.getAge());

        mockMvc.perform(get("/rest/group/get-all-group-student/" + name))
                .andExpect(status().isOk())
                .andExpect(content().string(entityData2));
    }
}