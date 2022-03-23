package com.webjournal.Entity;

import org.json.JSONArray;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@SuppressWarnings("JpaAttributeTypeInspection")
@Entity
@Table(name = "teacher_list")
public class Teacher {

    @Id
    @GeneratedValue
    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "classes_and_subjects")
    @Convert(converter = TeacherConverter.class)
    private Map<String, Object> classesAndSubjects;


    public Map<String, Object> getClassesAndSubjects() {
        return classesAndSubjects;
    }

    public void setClassesAndSubjects(Map<String, Object> classesAndSubjects) {
        this.classesAndSubjects = classesAndSubjects;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "username='" + username + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", classesAndSubjects=" + classesAndSubjects +
                '}';
    }
}
