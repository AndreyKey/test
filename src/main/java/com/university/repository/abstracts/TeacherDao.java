package com.university.repository.abstracts;

import com.university.model.Group;
import com.university.model.Teacher;

import java.math.BigInteger;
import java.util.List;

public interface TeacherDao extends GenericDao<BigInteger, Teacher>{
    void addGroupToTeacher(String groupName, String teacherId);
    List<Group> getAllGroupsByName(String teacherName);
}
