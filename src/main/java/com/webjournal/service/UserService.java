package com.webjournal.service;

import com.webjournal.entity.Role;
import com.webjournal.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    boolean saveUser(User user);
    List<User> getUsersByRole(Set<Role> roles);
    void deleteUser(Long id);
    User getUserById(Long id);
}
