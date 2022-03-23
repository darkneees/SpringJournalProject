package com.webjournal.Service;

import com.webjournal.Entity.Teacher;
import com.webjournal.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;


    @Override
    public Teacher getTeacherByUsername(String username) {
        return teacherRepository.getTeacherByUsername(username);
    }

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }
}
