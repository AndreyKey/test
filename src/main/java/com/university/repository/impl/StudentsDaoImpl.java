package com.university.repository.impl;

import com.university.model.Student;
import com.university.repository.abstracts.StudentDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class StudentsDaoImpl extends AbstractDao<BigInteger, Student> implements StudentDao {
}
