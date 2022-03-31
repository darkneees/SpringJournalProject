package com.webjournal.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "pupils")
public class Pupil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "class")
    private String classP;

    @Type(type = "json")
    @Column(name = "data", columnDefinition = "json")
    private Map<String, List<Map<String, String>>> data;

    public Pupil() {
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

    public String getClassP() {
        return classP;
    }

    public void setClassP(String classP) {
        this.classP = classP;
    }

    public Map<String, List<Map<String, String>>> getData() {
        return data;
    }

    public void setData(Map<String, List<Map<String, String>>> data) {
        this.data = data;
    }
}
