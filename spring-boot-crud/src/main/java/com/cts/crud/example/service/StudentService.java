package com.cts.crud.example.service;

import com.cts.crud.example.repository.StudentRepository;
import com.cts.crud.example.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.SQLException;
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
        try {
            if (repository.existsById(id) == false) {
                throw new NullPointerException("Id " + id + " is not present in the database");
            }
            repository.deleteById(id);
            return "Student deleted !!! " + id;
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return "Id " + id + " not found";
        }
    }

    public Student updateStudent(Student student) {
        Student existingStudent = repository.findById(student.getId()).orElse(null);
        existingStudent.setName(student.getName());
        existingStudent.setMobile(student.getMobile());
        existingStudent.setEmail(student.getEmail());
        return repository.save(existingStudent);
    }

    @SuppressWarnings("unchecked")
    public List<Student> getStudentInfo() {
        return em.createNamedStoredProcedureQuery("StudentProcedure1").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Student> getStudentInfoByName(String n) {
        return em.createNamedStoredProcedureQuery("StudentProcedure2")
                .setParameter("names", n).getResultList();
    }

    @SuppressWarnings("unchecked")
    public String insertStudentsInfo(Student student) {
        try {
            if (repository.countByName(student.getName()) == 0) {
                em.createNamedStoredProcedureQuery("StudentProcedure3")
                        .setParameter("ids1", student.getId())
                        .setParameter("names1", student.getName())
                        .setParameter("mobiles", student.getMobile())
                        .setParameter("emails", student.getEmail()).executeUpdate();
                return "Student Inserted !!! " + student.getId();
            }
            throw new SQLException("Duplicate records are not allowed !!!");
        }
        catch (SQLException e) {
            System.out.println(e);
            return "Name " + student.getName() + " is already present";
        }
    }

    @SuppressWarnings("unchecked")
    public String updateStudentsInfo(Student student) {
        try {
            if (repository.existsById(student.getId()) == false) {
                throw new NullPointerException("Id " + student.getId() + " is not present in the database");
            }
            em.createNamedStoredProcedureQuery("StudentProcedure4")
                    .setParameter("ids2", student.getId())
                    .setParameter("names2", student.getName())
                    .setParameter("mobiles1", student.getMobile())
                    .setParameter("emails1", student.getEmail()).executeUpdate();
            return "Student Updated !!! " + student.getId();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return "Id " + student.getId() + " not found";
        }
    }

    @SuppressWarnings("unchecked")
    public String deleteStudentsInfo(int i) {
        try {
            if (repository.existsById(i) == false) {
                throw new NullPointerException("Id " + i + " is not present in the database");
            }
            em.createNamedStoredProcedureQuery("StudentProcedure5").setParameter("ids", i).execute();
            return "Student deleted successfully !!!" + i;
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return "Id " + i + " not found ";
        }
    }

    @SuppressWarnings("unchecked")
    public String insertmulStudentsInfo(List<Student> students, int records) {
        for (int i = 0; i < records; i++) {
            em.createNamedStoredProcedureQuery("StudentProcedure6")
                    .setParameter("ids3", students.get(i).getId())
                    .setParameter("names3", students.get(i).getName())
                    .setParameter("mobiles2", students.get(i).getMobile())
                    .setParameter("emails2", students.get(i).getEmail()).executeUpdate();
        }
            return "Students Inserted !!!";
    }
}
