package com.webjournal.repository;

import com.webjournal.entity.Role;
import com.webjournal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    List<User> findByRolesIn(Set<Role> roles);
}
