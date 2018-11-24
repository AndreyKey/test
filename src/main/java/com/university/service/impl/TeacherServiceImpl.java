package com.university.service.impl;

import com.university.model.Group;
import com.university.model.Teacher;
import com.university.repository.abstracts.TeacherDao;
import com.university.service.abstracts.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherDao teacherDao;

    @Autowired
    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public void add(Teacher teacher) {
        teacherDao.persist(teacher);
    }

    @Override
    public List<Group> getAllGroupsByName(String teacherName) {
        return teacherDao.getAllGroupsByName(teacherName);
    }

    @Override
    public void addGroupToTeacher(String groupName, String teacherId) {
        teacherDao.addGroupToTeacher(groupName, teacherId);
    }
}
