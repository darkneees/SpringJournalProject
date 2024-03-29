package com.webjournal.service.user;

import com.webjournal.entity.Role;
import com.webjournal.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    boolean saveUser(User user);
    List<User> getUsersByRole(Set<Role> roles);
    void deleteUser(Long id);
    User getUserById(Long id);
    User getUserByUsername(String username);
    void editUserById(Long id, String username, String password, String firstName, String lastName);
}
