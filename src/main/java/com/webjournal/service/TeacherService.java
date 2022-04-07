package com.webjournal.service;

import com.webjournal.entity.Teacher;
import com.webjournal.entity.User;

public interface TeacherService {

    Teacher getTeacher(Long id);
    Teacher changeTeacherClass(User user, String selectSubject, String classP);
    void deleteWhereSubject(User user, String subject);
    Teacher deleteClassInSubject(User user, String classP, String subject);
}
