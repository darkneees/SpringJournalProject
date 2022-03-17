package com.webjournal.Entity;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@SuppressWarnings("JpaAttributeTypeInspection")
@Entity
@Table(name = "pupils_list")
public class Pupil {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "study_class")
    private String studyClass;

    @Column(name = "marks")
    @Convert(converter = PupilConverter.class)
    private Map<String, Object> marks;

    @Transient
    private Map<String, Object> currentSubjectMarks;

    public Map<String, Object> getCurrentSubjectMarks() {
        return currentSubjectMarks;
    }

    public void setCurrentSubjectMarks(Map<String, Object> currentSubjectMarks) {
        this.currentSubjectMarks = currentSubjectMarks;
    }

    public Map<String, Object> getMarks() {
        return marks;
    }

    public void setMarks(Map<String, Object> marks) {
        this.marks = marks;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudyClass() {
        return studyClass;
    }

    public void setStudyClass(String studyClass) {
        this.studyClass = studyClass;
    }


    @Override
    public String toString() {
        return "Pupil{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studyClass='" + studyClass + '\'' +
                ", marks=" + marks +
                '}';
    }
}
