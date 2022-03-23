package com.webjournal.Service;

import com.webjournal.Entity.Role;
import com.webjournal.Entity.User;
import com.webjournal.Repository.RoleRepository;
import com.webjournal.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public boolean addUserTeacher(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if(userFromDb != null) {
            return false;
        }

        userFromDb.setUsername(user.getUsername());
        userFromDb.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userFromDb.setRoles(Collections.singleton(new Role(1L, "ROLE_TEACHER")));
        userRepository.save(userFromDb);

        return true;
    }
}
