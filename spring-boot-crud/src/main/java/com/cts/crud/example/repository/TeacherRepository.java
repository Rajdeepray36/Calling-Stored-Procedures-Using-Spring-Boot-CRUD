package com.cts.crud.example.repository;

import com.cts.crud.example.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    Teacher findByName(String name);
    long countByName(String name);
}

