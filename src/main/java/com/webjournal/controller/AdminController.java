package com.webjournal.controller;

import com.google.gson.Gson;
import com.webjournal.entity.*;
import com.webjournal.service.pupil.PupilServiceImpl;
import com.webjournal.service.role.RoleServiceImpl;
import com.webjournal.service.teacher.TeacherServiceImpl;
import com.webjournal.service.user.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    final UserServiceImpl userService;

    final TeacherServiceImpl teacherService;

    final PupilServiceImpl pupilService;

    final RoleServiceImpl roleService;

    public AdminController(UserServiceImpl userService, TeacherServiceImpl teacherService, PupilServiceImpl pupilService, RoleServiceImpl roleService, Gson json) {
        this.userService = userService;
        this.teacherService = teacherService;
        this.pupilService = pupilService;
        this.roleService = roleService;

    }


    @GetMapping("/admin")
    public String getPupilsPage(Model model) {
        List<User> teachers = userService.getUsersByRole(Collections.singleton(new Role(2L)));

        model.addAttribute("pupils", pupilService.getAllPupils());
        model.addAttribute("teachers", teachers);
        List<Role> role = roleService.getRoles();
        role.removeIf(r -> r.getId() == 1);
        model.addAttribute("roles", role);
        model.addAttribute("subjects", Subjects.values());
        return "admin";
    }

    @GetMapping("/admin/add")
    public String getFormAddAdmin(Model model){
        List<Role> role = roleService.getRoles();
        role.removeIf(r -> r.getId() == 1);
        model.addAttribute("roles", role);
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
            user.setRoles(Collections.singleton(new Role(2L, "ROLE_TEACHER", "Преподаватель")));
            user.setTeacher(new Teacher());
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

    @GetMapping("/admin/pupils/edit/{id}")
    public String getEditPupilPage(@PathVariable Long id, Model model){
        model.addAttribute("pupil", pupilService.findPupilById(id));
        return "formEditPupil";
    }

    @PostMapping("/admin/pupils/edit")
    public RedirectView requestEditPupil(@RequestParam("id") Long id,
                                         @RequestParam("firstName") String firstName,
                                         @RequestParam("lastName") String lastName,
                                         @RequestParam("classP") String classP) {

        pupilService.editPupilById(id, firstName, lastName, classP);

        return new RedirectView("/admin");
    }


    @DeleteMapping("/admin/pupils/delete/{id}")
    @ResponseBody
    public Map<String, String> deletePupil(@PathVariable Long id) {
        pupilService.deletePupilById(id);
        return Map.of("result", "success");
    }


    @PostMapping("/admin/delete/{id}")
    @ResponseBody
    public Map<String, String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return Map.of("result", "success");
    }

    @PostMapping("/admin/add/class/{id}")
    @ResponseBody
    public Map<Object, Object> addTeachClass(@PathVariable Long id, @RequestParam String selectSubject,
                                             @RequestParam String classP){

        Teacher teacher = teacherService.changeTeacherClass(userService.getUserById(id), classP, selectSubject);
        return Map.of("result", "success", "data", teacher.getClassP().get(selectSubject));
    }

    @PostMapping("/admin/delete/subject/{id}")
    @ResponseBody
    public Map<String, String> deleteTeachSubject(@PathVariable Long id, @RequestParam String subject) {
        teacherService.deleteWhereSubject(userService.getUserById(id), subject);

        return Map.of("result", "success");
    }

    @PostMapping("/admin/delete/class/{id}")
    @ResponseBody
    public Map<String, Serializable> deleteTeachClass(@PathVariable Long id,
                                                      @RequestParam("selectedSubject") String selectedSubject,
                                                      @RequestParam("selectedClass") String selectedClass) {

        Teacher teacher = teacherService.deleteClassInSubject(userService.getUserById(id), selectedClass, selectedSubject);

        return Map.of(
                "result", "success",
                "data", teacher.getClassP().get(selectedSubject) != null ? teacher.getClassP().get(selectedSubject) : "");

    }

    private User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userService.getUserByUsername(username);
    }
}