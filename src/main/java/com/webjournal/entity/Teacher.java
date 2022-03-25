package com.webjournal.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

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

    @Transient
    @ManyToMany(mappedBy = "teachers")
    private List<User> user;


    @Type(type = "json")
    @Column(name = "class", columnDefinition = "json")
    private Map<String, Object> classP;

    @Type(type = "json")
    @Column(name = "subject", columnDefinition = "json")
    private Map<String, Object> subject;

    public Teacher() {
    }

    public Teacher(Long id) {
        this.id = id;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public Map<String, Object> getClassP() {
        return classP;
    }

    public void setClassP(Map<String, Object> classP) {
        this.classP = classP;
    }

    public Map<String, Object> getSubject() {
        return subject;
    }

    public void setSubject(Map<String, Object> subject) {
        this.subject = subject;
    }
}
