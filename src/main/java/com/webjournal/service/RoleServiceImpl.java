package com.webjournal.service;

import com.webjournal.entity.Role;
import com.webjournal.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }
}
