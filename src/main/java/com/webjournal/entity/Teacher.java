package com.webjournal.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.*;

@SuppressWarnings("JpaAttributeTypeInspection")
@Entity
@TypeDef(
        name = "json",
        typeClass = JsonType.class
)
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Type(type = "json")
    @Column(name = "classes_and_subjects", columnDefinition = "json")
    private Map<String, ArrayList<String>> classP;

    public Teacher() {
    }

    public Teacher(Long id) {
        this.id = id;
    }

    public Map<String, ArrayList<String>> getClassP() {
        return classP;
    }

    public void setClassP(Map<String, ArrayList<String>> classP) {
        this.classP = classP;
    }

    public void addClassP(String key, String value){
        if(classP == null) {
            classP = new HashMap<>();
            classP.put(key, new ArrayList<>(List.of(value)));
        } else {
            if(classP.containsKey(key)) {
                ArrayList<String> list = classP.get(key);
                if(!list.contains(value)) {
                    list.add(value);
                    classP.replace(key, list);
                }
            } else {
            classP.put(key, new ArrayList<>(List.of(value)));
            }
        }
    }

    public void deleteClassP(String key) {
        classP.remove(key);
    }

    public void deleteSubjectInClass(String key, String subject){
        if(classP.containsKey(key)) {
            ArrayList<String> list = classP.get(key);
            list.remove(subject);
            if(list.isEmpty()) deleteClassP(key);
            else classP.replace(key, list);
        }
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", classP=" + classP +
                '}';
    }
}
