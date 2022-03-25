package com.webjournal.controller;


import com.webjournal.entity.Pupil;
import com.webjournal.entity.Role;
import com.webjournal.entity.User;
import com.webjournal.service.PupilServiceImpl;
import com.webjournal.service.TeacherServiceImpl;
import com.webjournal.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    TeacherServiceImpl teacherService;

    @Autowired
    PupilServiceImpl pupilService;


    @GetMapping("/admin")
    public String getAdminPage(Model model){

        List<User> teachers = userService.getUsersByRole(Collections.singleton(new Role(2L)));
        System.out.println(teachers);

        model.addAttribute("teachers", teachers);

        return "admin";
    }

    @GetMapping("/admin/add")
    public String getFormAddAdmin(){
        return "formAdd";
    }

    @PostMapping("/admin/add")
    public String addAdminInDb(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName, @RequestParam("selectRole") String role, @RequestParam("classP") String classP){

        if (role.equals("PUPIL")) {

            Pupil pupil = new Pupil();
            pupil.setFirstName(firstName);
            pupil.setLastName(lastName);
            pupil.setClassP(classP);

            pupilService.addPupil(pupil);

            return "formAdd";

        } else {

            User user = new User();

            if (role.equals("ROLE_ADMIN"))
                user.setRoles(Collections.singleton(new Role(1L, "ROLE_ADMIN")));

            else if (role.equals("ROLE_TEACHER"))
                user.setRoles(Collections.singleton(new Role(2L, "ROLE_TEACHER")));

            user.setUsername(username);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            userService.saveUser(user);

            return "formAdd";
        }

    }


}
