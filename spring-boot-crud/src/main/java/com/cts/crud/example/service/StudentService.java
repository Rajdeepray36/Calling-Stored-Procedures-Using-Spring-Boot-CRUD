package com.cts.crud.example.service;

import com.cts.crud.example.repository.StudentRepository;
import com.cts.crud.example.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;
    @Autowired
    private EntityManager em;

    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    public List<Student> saveStudents(List<Student> students) {
        return repository.saveAll(students);
    }

    public List<Student> getStudent() {
        return repository.findAll();
    }

    public Student getStudentById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Student getStudentByName(String name) {
        return repository.findByName(name);
    }

    public String deleteStudent(int id) {
        repository.deleteById(id);
        return "student removed !! " + id;
    }

    public Student updateStudent(Student student) {
        Student existingStudent = repository.findById(student.getId()).orElse(null);
        existingStudent.setName(student.getName());
        existingStudent.setMobile(student.getMobile());
        existingStudent.setEmail(student.getEmail());
        return repository.save(existingStudent);
    }

    @SuppressWarnings("unchecked")
    public List<Student> getStudentInfo(){
        return em.createNamedStoredProcedureQuery("StudentProcedure1").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Student> getStudentInfoByName(String n){
        return em.createNamedStoredProcedureQuery("StudentProcedure2").setParameter("names", n).getResultList();
    }

    @SuppressWarnings("unchecked")
    public String insertStudentsInfo(Student student){
        em.createNamedStoredProcedureQuery("StudentProcedure3").setParameter("ids1", student.getId()).setParameter("names1", student.getName()).setParameter("mobiles", student.getMobile()).setParameter("emails", student.getEmail()).executeUpdate();
        return "Student Inserted !!! " +student.getId();
    }

    @SuppressWarnings("unchecked")
    public String updateStudentsInfo(Student student){
        repository.findById(student.getId()).orElse(null);
        em.createNamedStoredProcedureQuery("StudentProcedure4").setParameter("ids2", student.getId()).setParameter("names2", student.getName()).setParameter("mobiles1", student.getMobile()).setParameter("emails1", student.getEmail()).executeUpdate();
        return "Student Updated !!! " +student.getId();
    }

    @SuppressWarnings("unchecked")
    public String deleteStudentsInfo(int i){
      em.createNamedStoredProcedureQuery("StudentProcedure5").setParameter("ids", i).execute();
      return "student removed !! " + i;
    }

}
