package com.webjournal.Service;

import com.webjournal.Entity.Teacher;

import java.util.List;

public interface TeacherService {

   Teacher getTeacherByUsername(String username);
   List<Teacher> getAllTeacher();
}
