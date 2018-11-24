package com.university.service.abstracts;

import com.university.model.Group;

public interface GroupService {
    void addStudentsToGroup(String groupName, String studentName);

    void add(Group group);

    Group getByName(String name);

    void delete(Group group);
}
