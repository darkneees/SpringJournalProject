package com.webjournal.service;

import com.webjournal.entity.Teacher;
import com.webjournal.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public void addPupil(Teacher teacher) {
        teacherRepository.save(teacher);
    }
}
