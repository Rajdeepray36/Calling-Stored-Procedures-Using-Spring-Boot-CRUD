package com.cts.crud.example.controller;

import com.cts.crud.example.entity.Student;
import com.cts.crud.example.entity.Teacher;
import com.cts.crud.example.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @PostMapping("/addTeacher")
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        return service.saveTeacher(teacher);
    }

    @PostMapping("/addTeachers")
    public List<Teacher> addTeachers(@RequestBody List<Teacher> teachers) {
        return service.saveTeachers(teachers);
    }

    @GetMapping("/teachers")
    public List<Teacher> findAllTeachers() {
        return service.getTeacher();
    }

    @GetMapping("/teacherById/{id}")
    public Teacher findTeacherById(@PathVariable int id) {
        return service.getTeacherById(id);
    }

    @GetMapping("/teacherByName/{name}")
    public Teacher findTeacherByName(@PathVariable String name) {
        return service.getTeacherByName(name);
    }

    @PutMapping("/update")
    public Teacher updateTeacher(@RequestBody Teacher teacher) {
        return service.updateTeacher(teacher);
    }

    @DeleteMapping("/deleteTeacher/{id}")
    public String deleteTeacher(@PathVariable int id) {
        return service.deleteTeacher(id);
    }

    @GetMapping("/fetchTeachers")
    public List<Teacher> findTeachers(){
        return service.getTeacherInfo();
    }

    @GetMapping("/fetchTeachersByName/{name}")
    public List<Teacher> findTeachersByName(@PathVariable String name){
        return service.getTeacherInfoByName(name);
    }

    @PostMapping("/insertTeacherDetails")
    public String insertTeachers(@RequestBody Teacher teacher){
        return service.insertTeachersInfo(teacher);
    }

    @PutMapping("/updateTeacherDetails/{id}")
    public String updateTeachers(@RequestBody Teacher teacher){
        return service.updateTeachersInfo(teacher);
    }

    @DeleteMapping("/deleteTeachersByid/{id}")
    public String deleteTeachersById(@PathVariable int id){
        return service.deleteTeachersInfo(id);
    }
}
