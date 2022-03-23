package com.webjournal.Repository;

import com.webjournal.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {

    public Teacher getTeacherByUsername(String username);
    public List<Teacher> findAll();

}
