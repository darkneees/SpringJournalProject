package com.webjournal.controller;

import com.webjournal.entity.Pupil;
import com.webjournal.entity.Role;
import com.webjournal.entity.Teacher;
import com.webjournal.entity.User;
import com.webjournal.service.PupilServiceImpl;
import com.webjournal.service.RoleServiceImpl;
import com.webjournal.service.TeacherServiceImpl;
import com.webjournal.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
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

    @Autowired
    RoleServiceImpl roleService;


    @GetMapping("/admin")
    public String getAdminPage(Model model){
        List<User> teachers = userService.getUsersByRole(Collections.singleton(new Role(2L)));
        model.addAttribute("teachers", teachers);

        return "admin";
    }

    @GetMapping("/admin/add")
    public String getFormAddAdmin(Model model){
        model.addAttribute("roles", roleService.getRoles());
        return "formAdd";
    }

    @PostMapping("/admin/add")
    public RedirectView addAdminInDb(@RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     @RequestParam("first_name") String firstName,
                                     @RequestParam("last_name") String lastName,
                                     @RequestParam("selectRole") String role,
                                     @RequestParam("classP") String classP){

        if (role.equals("Ученик")) {
            Pupil pupil = new Pupil();
            pupil.setFirstName(firstName);
            pupil.setLastName(lastName);
            pupil.setClassP(classP);
            pupilService.addPupil(pupil);

        } else {
            User user = new User();
            if (role.equals("ROLE_ADMIN"))
                user.setRoles(Collections.singleton(new Role(1L, "ROLE_ADMIN", "Администратор")));

            else if (role.equals("ROLE_TEACHER")) {
                user.setRoles(Collections.singleton(new Role(2L, "ROLE_TEACHER", "Преподаватель")));
                user.setTeacher(new Teacher());
            }
            user.setUsername(username);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            userService.saveUser(user);
        }
        return new RedirectView("/admin/add");

    }

    @PostMapping("/admin/delete/{id}")
    public RedirectView deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new RedirectView("/admin");
    }


    @PostMapping("/admin/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getRoles());

        return "formEdit";
    }

    @PostMapping("/admin/add/class/{id}")
    public RedirectView addTeachClass(@PathVariable Long id, @RequestParam String selectSubject,
                                      @RequestParam String classP){

        teacherService.changeTeacherClass(userService.getUserById(id), classP, selectSubject);
        return new RedirectView("/admin");
    }

    @PostMapping("/admin/delete/subject/{id}/{class_t}")
    @ResponseBody
    public String deleteTeachClass(@PathVariable Long id, @PathVariable String class_t) {

        teacherService.deleteWhereClass(userService.getUserById(id), class_t);

        return "{result: success}";
    }

    @PostMapping("/admin/delete/class/{id}/{selectedSubject}")
    @ResponseBody
    public String deleteTeachSubject(@PathVariable Long id, @PathVariable String selectedSubject,
                                           @RequestParam("selectedClass") String selectedClass) {

        Teacher teacher = teacherService.deleteSubjectInClass(userService.getUserById(id), selectedClass, selectedSubject);
        System.out.println(teacher.toString());

        return "{\"result\": \"success\", \"data\": \"" + teacher.getClassP().get(selectedSubject) + "\"}";

    }
}
