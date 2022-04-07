package com.webjournal.service;

import com.webjournal.entity.Teacher;
import com.webjournal.entity.User;
import com.webjournal.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;


    @Override
    public Teacher getTeacher(Long id){
        Optional<Teacher> teacher = teacherRepository.findById(id);
        return teacher.get();
    }

    @Override
    public Teacher changeTeacherClass(User user, String selectSubject, String classP) {
        Teacher teacher = user.getTeacher();
        ArrayList<String> list = new ArrayList<>();
        list.add(selectSubject);
        teacher.addClassP(classP, list);

        teacherRepository.save(teacher);
        return teacher;
    }

    @Override
    public void deleteWhereSubject(User user, String subject) {
        Teacher teacher = user.getTeacher();
        teacher.deleteClassP(subject);
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher deleteClassInSubject(User user, String classP, String subject){
        Teacher teacher = user.getTeacher();
        teacher.deleteSubjectInClass(subject, classP);

        teacherRepository.save(teacher);
        return teacher;
    }



}
