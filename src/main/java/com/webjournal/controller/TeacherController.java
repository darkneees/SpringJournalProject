package com.webjournal.controller;

import com.webjournal.entity.User;
import com.webjournal.service.PupilServiceImpl;
import com.webjournal.service.TeacherServiceImpl;
import com.webjournal.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class TeacherController {

    @Autowired
    TeacherServiceImpl teacherService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    PupilServiceImpl pupilService;

    @GetMapping("/teacher")
    public String getTeacherPage(Model model){

        model.addAttribute("user", getUser());

        return "teacher";
    }

    @PostMapping("/teacher")
    public String getTeacherPageWithMarks(Model model,
                                          @RequestParam("selectedClass") String selectedClass,
                                          @RequestParam("selectedSubject") String selectedSubject) {

        model.addAttribute("pupils", pupilService.findPupilsByClassP(selectedClass));
        model.addAttribute("subject", selectedSubject);
        model.addAttribute("user", getUser());

        return "teacher";
    }

    @PostMapping("/teacher/mark/{id}/{subject}")
    public RedirectView addPupilMark(@PathVariable Long id, @PathVariable String subject,
                               @RequestParam("date") String date, @RequestParam("mark") String mark) {

        pupilService.addMarkPupil(id, subject, date, mark);

        return new RedirectView("/teacher");
    }

    private User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userService.getUserByUsername(username);
    }

}
