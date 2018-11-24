package com.university.controllers.rest;

import com.university.model.Group;
import com.university.service.abstracts.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/rest/group")
public class GroupRestController {
    private final GroupService groupService;

    @Autowired
    public GroupRestController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(path = "/create-new-group/{name}")
    public ResponseEntity createNewGroup(@PathVariable("name") String name) {
        Group group = new Group(name);
        groupService.add(group);
        return ResponseEntity.ok("");
    }

    @GetMapping(path = "/get-group-by-name/{name}")
    public ResponseEntity<Group> getGroupByName(@PathVariable("name") String name) {
        Group group = groupService.getByName(name);
        return ResponseEntity.status(200).body(group);
    }

    @PostMapping(path = "/add-students-to-group/group/{groupName}/student/{studentName}")
    public ResponseEntity<String> addStudentsToGroup(@PathVariable("groupName") String groupName,
                                                     @PathVariable("studentName") String studentName) {

        groupService.addStudentsToGroup(groupName, studentName);
        return ResponseEntity.ok("");
    }
}
