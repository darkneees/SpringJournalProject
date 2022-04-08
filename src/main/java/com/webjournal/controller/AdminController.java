package com.webjournal.controller;

import com.webjournal.entity.Pupil;
import com.webjournal.entity.Role;
import com.webjournal.entity.Teacher;
import com.webjournal.entity.User;
import com.webjournal.service.pupil.PupilServiceImpl;
import com.webjournal.service.role.RoleServiceImpl;
import com.webjournal.service.teacher.TeacherServiceImpl;
import com.webjournal.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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

    /////////////////////////// Страницы со списками пользователей /////////////////////////

    @GetMapping("/admin")
    public String getPupilsPage(Model model) {
        List<User> admins = userService.getUsersByRole(Collections.singleton(new Role(1L)));
        admins.removeIf(user -> user.getUsername().equals(getUser().getUsername()));
        List<User> teachers = userService.getUsersByRole(Collections.singleton(new Role(2L)));

        model.addAttribute("pupils", pupilService.getAllPupils());
        model.addAttribute("admins", admins);
        model.addAttribute("teachers", teachers);
        model.addAttribute("roles", roleService.getRoles());
        return "admin";
    }

    // Страница с добавлением пользователя
    @GetMapping("/admin/add")
    public String getFormAddAdmin(Model model){
        model.addAttribute("roles", roleService.getRoles());
        return "formAdd";
    }

    // Добавление пользователя через форму выше
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

    @GetMapping("/admin/edit/{id}")
    public String getEditAdminPage(@PathVariable Long id, Model model){
        model.addAttribute("role", userService.getUserById(id).getRoles().iterator().next().getPretty_name());
        model.addAttribute("user", userService.getUserById(id));
        return "formEdit";
    }

    @PostMapping("/admin/edit")
    public RedirectView requestEditAdmin(@RequestParam("id") Long id,
                                  @RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  @RequestParam("firstName") String firstName,
                                  @RequestParam("lastName") String lastName) {

        userService.editUserById(id, username, password, firstName, lastName);
        return new RedirectView("/admin");
    }

    @GetMapping("/admin/editPupil/{id}")
    public String getEditPupilPage(@PathVariable Long id, Model model){
        model.addAttribute("pupil", pupilService.findPupilById(id));
        return "formEditPupil";
    }

    @PostMapping("/admin/editPupil")
    public RedirectView requestEditPupil(@RequestParam("id") Long id,
                                         @RequestParam("firstName") String firstName,
                                         @RequestParam("lastName") String lastName,
                                         @RequestParam("classP") String classP) {

        pupilService.editPupilById(id, firstName, lastName, classP);

        return new RedirectView("/admin");
    }


    @PostMapping("/admin/deletePupil/{id}")
    @ResponseBody
    public String deletePupil(@PathVariable Long id) {
        pupilService.deletePupilById(id);
        return "{\"result\": \"success\"}";
    }


    @PostMapping("/admin/delete/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "{\"result\": \"success\"}";
    }

    @PostMapping("/admin/add/class/{id}")
    @ResponseBody
    public String addTeachClass(@PathVariable Long id, @RequestParam String selectSubject,
                                      @RequestParam String classP){

        Teacher teacher = teacherService.changeTeacherClass(userService.getUserById(id), classP, selectSubject);

        return "{\"result\": \"success\", \"data\": \"" + teacher.getClassP().get(selectSubject) + "\"}";
    }

    @PostMapping("/admin/delete/subject/{id}")
    @ResponseBody
    public String deleteTeachSubject(@PathVariable Long id, @RequestParam String subject) {
        teacherService.deleteWhereSubject(userService.getUserById(id), subject);

        return "{\"result\": \"success\"}";
    }

    @PostMapping("/admin/delete/class/{id}")
    @ResponseBody
    public String deleteTeachClass(@PathVariable Long id,
                                     @RequestParam("selectedSubject") String selectedSubject,
                                     @RequestParam("selectedClass") String selectedClass) {

        Teacher teacher = teacherService.deleteClassInSubject(userService.getUserById(id), selectedClass, selectedSubject);

        return "{\"result\": \"success\", \"data\": \"" + teacher.getClassP().get(selectedSubject) + "\"}";

    }

    private User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userService.getUserByUsername(username);
    }
}