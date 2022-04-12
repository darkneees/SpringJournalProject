package com.webjournal;

import com.webjournal.entity.Role;
import com.webjournal.entity.User;
import com.webjournal.service.role.RoleServiceImpl;
import com.webjournal.service.user.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Collections;

@SpringBootApplication
public class WebJournalApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WebJournalApplication.class, args);
        UserServiceImpl userService = context.getBean(UserServiceImpl.class);
        RoleServiceImpl roleService = context.getBean(RoleServiceImpl.class);

        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_ADMIN");
        role.setPretty_name("Администратор");
        roleService.addRole(role);
        role.setId(2L);
        role.setName("ROLE_TEACHER");
        role.setPretty_name("Преподаватель");
        roleService.addRole(role);

        User user = new User();
        user.setUsername("Admin");
        user.setPassword("Admin");
        user.setFirstName("Admin");
        user.setLastName("Admin");
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_ADMIN", "Администратор")));
        userService.saveUser(user);

    }
}
