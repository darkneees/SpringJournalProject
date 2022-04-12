package com.webjournal.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name_role")
    private String name;

    @Column(name = "pretty_name")
    private String pretty_name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(){}
    public Role(Long id){
        this.id = id;
    }

    public Role(Long id, String name, String pretty_name) {
        this.id = id;
        this.name = name;
        this.pretty_name = pretty_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getPretty_name() {
        return pretty_name;
    }

    public void setPretty_name(String pretty_name) {
        this.pretty_name = pretty_name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
