package com.webjournal.service;

import com.webjournal.entity.Role;
import com.webjournal.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> getAllUsers();
    boolean saveUser(User user);
    List<User> getUsersByRole(Set<Role> roles);

}
