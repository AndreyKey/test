package com.university.controllers.rest;

import com.university.model.Group;
import com.university.model.Teacher;
import com.university.repository.impl.TeacherDaoImpl;
import com.university.service.abstracts.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/rest/teacher")
public class TeacherRestController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherRestController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @GetMapping(path = "/create-new-teacher/{teacherName}")
    public ResponseEntity<String> createNewTeacher(@PathVariable("teacherName") String teacherName) {
        Teacher teacher = new Teacher(teacherName);
        teacherService.add(teacher);
        return ResponseEntity.ok("");
    }

    @GetMapping(path = "/get-all-groups/{teacherName}")
    public ResponseEntity<List<Group>> getAllGroupFromTeacher(@PathVariable("teacherName") String teacherName) {
        List<Group> groups = teacherService.getAllGroupsByName(teacherName);
        return ResponseEntity.status(200).body(groups);
    }

    @GetMapping(path = "/add-groups-by-name/{teacherId}/{groupName}")
    public ResponseEntity<String> addGroupFromTeacher(@PathVariable("teacherId") String teacherId, @PathVariable("groupName") String groupName) {
        teacherService.addGroupToTeacher(groupName, teacherId);
        return ResponseEntity.ok("");
    }
}
