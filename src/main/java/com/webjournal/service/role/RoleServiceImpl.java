package com.webjournal.service.role;

import com.webjournal.entity.Role;
import com.webjournal.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    public boolean addRole(Role role){
        Role roleFromDB = roleRepository.findByName(role.getName());

        if(roleFromDB != null) {
            return false;
        }
        roleRepository.save(role);
        return true;
    }
}
