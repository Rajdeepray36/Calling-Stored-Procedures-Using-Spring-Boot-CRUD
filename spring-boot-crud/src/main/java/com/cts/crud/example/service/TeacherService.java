package com.cts.crud.example.service;

import com.cts.crud.example.entity.Teacher;
import com.cts.crud.example.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Transactional
@Service
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
        try {
            if (repository.existsById(id) == false){
                throw new NullPointerException("Id "+id+" is not present in the database");
            }
        repository.deleteById(id);
        return "teacher removed !! " + id;
    }
        catch(NullPointerException e){
            e.printStackTrace();
            return "Id "+id+" not found";
        }
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
        return em.createNamedStoredProcedureQuery("TeacherProcedure2")
                .setParameter("names", n).getResultList();
    }

    @SuppressWarnings("unchecked")
    public String insertTeachersInfo(Teacher teacher){
        try {
        if (repository.countByName(teacher.getName()) == 0) {
        em.createNamedStoredProcedureQuery("TeacherProcedure3")
                .setParameter("ids1", teacher.getId())
                .setParameter("names1", teacher.getName())
                .setParameter("mobiles", teacher.getMobile())
                .setParameter("emails", teacher.getEmail()).executeUpdate();
        return "Teacher Inserted !!! " +teacher.getId();
    }
        throw new SQLException("Duplicate records are not allowed !!!");
    }
    catch(SQLException e) {
        System.out.println(e);
        return "Name " + teacher.getName() + " is already present";
    }
    }

    @SuppressWarnings("unchecked")
    public String updateTeachersInfo(Teacher teacher){
        try {
            if (repository.existsById(teacher.getId()) == false) {
                throw new NullPointerException("Id " + teacher.getId() + " is not present in the database");
            }
        em.createNamedStoredProcedureQuery("TeacherProcedure4")
                .setParameter("ids2", teacher.getId())
                .setParameter("names2", teacher.getName())
                .setParameter("mobiles1", teacher.getMobile())
                .setParameter("emails1", teacher.getEmail()).executeUpdate();
        return "Teacher Updated !!!" +teacher.getId();
    }
        catch (NullPointerException e) {
            e.printStackTrace();
            return "Id " + teacher.getId() + " not found";
        }
    }

    @SuppressWarnings("unchecked")
    public String deleteTeachersInfo(int i){
        try {
            if(repository.existsById(i) == false){
                throw new NullPointerException("Id "+i+" is not present in the database");
            }
        em.createNamedStoredProcedureQuery("TeacherProcedure5").setParameter("ids", i).execute();
        return "Teacher removed !! " + i;
    }
        catch(NullPointerException e){
            e.printStackTrace();
            return "Id "+i+" not found ";
        }
    }

    @SuppressWarnings("unchecked")
    public String insertmulTeachersInfo(List<Teacher> teachers, int records) {
        for (int i = 0; i < records; i++) {
            em.createNamedStoredProcedureQuery("TeacherProcedure6")
                    .setParameter("ids3", teachers.get(i).getId())
                    .setParameter("names3", teachers.get(i).getName())
                    .setParameter("mobiles2", teachers.get(i).getMobile())
                    .setParameter("emails2", teachers.get(i).getEmail()).executeUpdate();
        }
        return "Teachers Inserted !!! ";
    }
}
