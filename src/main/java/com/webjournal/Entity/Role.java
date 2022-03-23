package com.webjournal.Entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authorities")
public class Role implements GrantedAuthority {

    @Id
    private Long id;
    private String name;

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(){}

    @Override
    public String getAuthority() {
        return getName();
    }
}
