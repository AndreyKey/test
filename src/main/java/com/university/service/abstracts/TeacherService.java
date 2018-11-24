package com.university.service.abstracts;

import com.university.model.Group;
import com.university.model.Teacher;

import java.util.List;

public interface TeacherService {
    void add(Teacher teacher);

    List<Group> getAllGroupsByName(String teacherName);

    void addGroupToTeacher(String groupName, String teacherId);
}
