package com.webjournal.service.teacher;

import com.webjournal.entity.Teacher;
import com.webjournal.entity.User;

import java.util.List;

public interface TeacherService {

    Teacher getTeacher(Long id);
    Teacher changeTeacherClass(User user, String selectSubject, String classP);
    void deleteWhereSubject(User user, String subject);
    Teacher deleteClassInSubject(User user, String classP, String subject);
    List<String> getClassesBySelectedSubject(Long id, String selectedSubject);
}
