package com.university.repository.impl;

import com.university.model.Group;
import com.university.repository.abstracts.GroupDao;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class GroupDaoImpl extends AbstractDao<BigInteger, Group> implements GroupDao {
}
