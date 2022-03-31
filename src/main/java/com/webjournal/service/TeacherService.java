package com.webjournal.service;

import com.webjournal.entity.Teacher;
import com.webjournal.entity.User;

public interface TeacherService {

    Teacher getTeacher(Long id);
    void changeTeacherClass(User user, String selectSubject, String classP);
    void deleteWhereClass(User user, String classP);
    void deleteSubjectInClass(User user, String classP, String subject);
}
