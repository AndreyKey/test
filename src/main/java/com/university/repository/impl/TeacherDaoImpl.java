package com.university.repository.impl;

import com.university.model.Group;
import com.university.model.Teacher;
import com.university.repository.abstracts.TeacherDao;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherDaoImpl extends AbstractDao<BigInteger, Teacher> implements TeacherDao {

    public void addGroupToTeacher(String groupName, String teacherId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(groupName));
        List<Group> groups = mongoTemplate.find(query, Group.class);
        Teacher teacher = mongoTemplate.findById(teacherId, Teacher.class);
        teacher.setGroups(groups);
        mongoTemplate.save(teacher);
    }

    public List<Group> getAllGroupsByName(String teacherName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(teacherName));
        Teacher teacher = mongoTemplate.findOne(query, Teacher.class);
        return teacher.getGroups();
    }
}
