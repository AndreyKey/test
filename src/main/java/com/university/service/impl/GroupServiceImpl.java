package com.university.service.impl;

import com.university.model.Group;
import com.university.model.Student;
import com.university.repository.abstracts.GroupDao;
import com.university.repository.abstracts.StudentDao;
import com.university.service.abstracts.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private final StudentDao studentDao;
    private final GroupDao groupDao;

    @Autowired
    public GroupServiceImpl(StudentDao studentDao, GroupDao groupDao) {
        this.studentDao = studentDao;
        this.groupDao = groupDao;
    }

    @Override
    public void addStudentsToGroup(String groupName, String studentName) {
        Student student = studentDao.getByName(studentName);
        Group group = groupDao.getByName(groupName);
        List<Student> studentsList = group.getStudents();
        if(studentsList==null) {
            studentsList = new ArrayList<>();
        }
        if(!studentsList.contains(student)) {
            studentsList.add(student);
        }
        group.setStudents(studentsList);
        groupDao.update(group);
    }

    @Override
    public void add(Group group) {
        groupDao.persist(group);
    }

    @Override
    public Group getByName(String name) {
        return groupDao.getByName(name);
    }

    @Override
    public void delete(Group group) {
        groupDao.delete(group);
    }
}
