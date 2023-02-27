package com.cts.crud.example.service;

import com.cts.crud.example.entity.Student;
import com.cts.crud.example.entity.Teacher;
import com.cts.crud.example.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
public class TeacherService {
    @Autowired
    private TeacherRepository repository;
    @Autowired
    private EntityManager em;

    public Teacher saveTeacher(Teacher teacher) {
        return repository.save(teacher);
    }

    public List<Teacher> saveTeachers(List<Teacher> teachers) {
        return repository.saveAll(teachers);
    }

    public List<Teacher> getTeacher() {
        return repository.findAll();
    }

    public Teacher getTeacherById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Teacher getTeacherByName(String name) {
        return repository.findByName(name);
    }

    public String deleteTeacher(int id) {
        repository.deleteById(id);
        return "teacher removed !! " + id;
    }

    public Teacher updateTeacher(Teacher teacher) {
        Teacher existingTeacher = repository.findById(teacher.getId()).orElse(null);
        existingTeacher.setName(teacher.getName());
        existingTeacher.setMobile(teacher.getMobile());
        existingTeacher.setEmail(teacher.getEmail());
        return repository.save(existingTeacher);
    }

    @SuppressWarnings("unchecked")
    public List<Teacher> getTeacherInfo(){
        return em.createNamedStoredProcedureQuery("TeacherProcedure1").getResultList();
}

    @SuppressWarnings("unchecked")
    public List<Teacher> getTeacherInfoByName(String n){
        return em.createNamedStoredProcedureQuery("TeacherProcedure2").setParameter("names", n).getResultList();
    }

    @SuppressWarnings("unchecked")
    public String insertTeachersInfo(Teacher teacher){
        em.createNamedStoredProcedureQuery("TeacherProcedure3").setParameter("ids1", teacher.getId()).setParameter("names1", teacher.getName()).setParameter("mobiles", teacher.getMobile()).setParameter("emails", teacher.getEmail()).executeUpdate();
        return "Teacher Inserted !!! " +teacher.getId();
    }

    @SuppressWarnings("unchecked")
    public String updateTeachersInfo(Teacher teacher){
        repository.findById(teacher.getId()).orElse(null);
        em.createNamedStoredProcedureQuery("TeacherProcedure4").setParameter("ids2", teacher.getId()).setParameter("names2", teacher.getName()).setParameter("mobiles1", teacher.getMobile()).setParameter("emails1", teacher.getEmail()).executeUpdate();
        return "Teacher Updated !!!" +teacher.getId();
    }

    @SuppressWarnings("unchecked")
    public String deleteTeachersInfo(int i){
        em.createNamedStoredProcedureQuery("TeacherProcedure5").setParameter("ids", i).execute();
        return "Teacher removed !! " + i;
    }

}
