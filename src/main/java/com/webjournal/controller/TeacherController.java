package com.webjournal.controller;

import com.google.gson.Gson;
import com.webjournal.entity.Pupil;
import com.webjournal.entity.User;
import com.webjournal.service.pupil.PupilServiceImpl;
import com.webjournal.service.teacher.TeacherServiceImpl;
import com.webjournal.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ResponseBody
    public String getPupilsTable(@RequestParam("selectedClass") String selectedClass,
                                 @RequestParam("selectedSubject") String selectedSubject){
        List<Pupil> pupils = pupilService.findPupilsByClassP(selectedClass);
        for(Pupil pupil: pupils) {
            if(pupil.getData() != null) {
                for (String key : pupil.getData().keySet()) {
                    if (!key.equals(selectedSubject)) pupil.getData().remove(key);
                }
            }
        }

        System.out.println(new Gson().toJson(pupils));

        return "{\"result\": \"success\", \"data\":" + new Gson().toJson(pupils) + "}";
    }

    @PostMapping("/teacher/classes")
    @ResponseBody
    public String getClasses(@RequestParam("id") Long id, @RequestParam("selectedSubject") String selectedSubject){
        List<String> list = teacherService.getClassesBySelectedSubject(id, selectedSubject);
        return "{\"result\": \"success\", \"data\": \"" + list + "\"}";
    }

    @PostMapping("/teacher/pupil/mark")
    @ResponseBody
    public String addPupilMark(@RequestParam("id") Long id,
                               @RequestParam("selectedSubject") String selectedSubject,
                               @RequestParam("date") String date,
                               @RequestParam("mark") String mark){

        pupilService.addMarkPupil(id, selectedSubject, date, mark);

        return "{\"result\": \"success\", \"date\": \"" + date + "\", \"mark\": \"" + mark + "\"}";
    }

    @PostMapping("/teacher/pupils/delete/mark")
    @ResponseBody
    public String deleteMark(@RequestParam("id") Long id,
                             @RequestParam("selectedSubject") String selectedSubject,
                             @RequestParam("date") String date){

        pupilService.deleteMarkByDate(id, selectedSubject, date);

        return "{\"result\": \"success\"}";
    }


    private User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userService.getUserByUsername(username);
    }

}
