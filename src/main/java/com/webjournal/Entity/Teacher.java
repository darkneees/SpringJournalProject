package com.webjournal.Entity;

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

    @Column(name = "classes")
    @Convert(converter = TeacherConverter.class)
    private List<Map<String, Object>> classes;

    @Column(name = "subjects")
    @Convert(converter = TeacherConverter.class)
    private List<Map<String, Object>> subjects;

    public List<Map<String, Object>> getClasses() {
        return classes;
    }

    public void setClasses(List<Map<String, Object>> classes) {
        this.classes = classes;
    }

    public List<Map<String, Object>> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Map<String, Object>> subjects) {
        this.subjects = subjects;
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
                ", classes=" + classes +
                ", subjects=" + subjects +
                '}';
    }
}
