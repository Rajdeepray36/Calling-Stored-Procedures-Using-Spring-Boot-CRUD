package com.cts.crud.example.controller;

import com.cts.crud.example.entity.Student;
import com.cts.crud.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody @Valid Student student) {
        return service.saveStudent(student);
    }

    @PostMapping("/addStudents")
    public List<Student> addStudents(@RequestBody List<@Valid Student> students) {
        return service.saveStudents(students);
    }

    @GetMapping("/students")
    public List<Student> findAllStudents() {
        return service.getStudent();
    }

    @GetMapping("/studentById/{id}")
    public Student findStudentById(@PathVariable int id) {
        return service.getStudentById(id);
    }

    @GetMapping("/studentByName/{name}")
    public Student findStudentByName(@PathVariable String name) {
        return service.getStudentByName(name);
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestBody @Valid Student student) {
        return service.updateStudent(student);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public String deleteStudents(@PathVariable int id) {
        return service.deleteStudent(id);
    }

    @GetMapping("/fetchStudents")
    public List<Student> findStudents(){
        return service.getStudentInfo();
    }

    @GetMapping("/fetchStudentsByName/{name}")
    public List<Student> findStudentsByName(@PathVariable String name){
        return service.getStudentInfoByName(name);
    }

    @PostMapping("/insertStudentDetails")
    public String insertStudents(@RequestBody @Valid Student student){
        return service.insertStudentsInfo(student);
    }

    @PostMapping("/insertmultStudentDetails")
    public String insertmulStudents(@RequestBody List<@Valid Student> students, int records){
        return service.insertmulStudentsInfo(students, records);
    }

    @PutMapping("/updateStudentDetails")
    public String updateStudents(@RequestBody @Valid Student student){
        return service.updateStudentsInfo(student);
    }

    @DeleteMapping("/deleteStudentsByid/{id}")
    public String deleteStudentsById(@PathVariable int id){
        return service.deleteStudentsInfo(id);
    }
}

