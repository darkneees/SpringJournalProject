package com.webjournal.controller;

import com.webjournal.entity.Pupil;
import com.webjournal.entity.User;
import com.webjournal.service.pupil.PupilServiceImpl;
import com.webjournal.service.teacher.TeacherServiceImpl;
import com.webjournal.service.user.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class TeacherController {

    final TeacherServiceImpl teacherService;

    final UserServiceImpl userService;

    final PupilServiceImpl pupilService;

    public TeacherController(TeacherServiceImpl teacherService, UserServiceImpl userService, PupilServiceImpl pupilService) {
        this.teacherService = teacherService;
        this.userService = userService;
        this.pupilService = pupilService;
    }

    @GetMapping("/teacher")
    public String getTeacherPage(Model model){
        model.addAttribute("user", getUser());
        return "teacher";
    }


    @PostMapping("/teacher")
    @ResponseBody
    public Map<String, Object> getPupilsTable(@RequestParam("selectedClass") String selectedClass,
                                              @RequestParam("selectedSubject") String selectedSubject){
        List<Pupil> pupils = pupilService.findPupilsByClassP(selectedClass);
        for(Pupil pupil: pupils) {
            if(pupil.getData() != null) pupil.getData().keySet().removeIf(key -> !Objects.equals(key, selectedSubject));
        }
        return Map.of("result", "success", "data", pupils);
    }

    @PostMapping("/teacher/classes")
    @ResponseBody
    public Map<String, Object> getClasses(@RequestParam("id") Long id, @RequestParam("selectedSubject") String selectedSubject){
        List<String> list = teacherService.getClassesBySelectedSubject(id, selectedSubject);
        return Map.of("result", "success", "data", list);
    }

    @PostMapping("/teacher/pupil/mark")
    @ResponseBody
    public Map<String, String> addPupilMark(@RequestParam("id") Long id,
                                            @RequestParam("selectedSubject") String selectedSubject,
                                            @RequestParam("date") String date,
                                            @RequestParam("mark") String mark){

        pupilService.addMarkPupil(id, selectedSubject, date, mark);

        return Map.of("result", "success", "date", date, "mark", mark);

    }

    @PostMapping("/teacher/pupils/delete/mark")
    @ResponseBody
    public Map<String, String> deleteMark(@RequestParam("id") Long id,
                                          @RequestParam("selectedSubject") String selectedSubject,
                                          @RequestParam("date") String date){

        pupilService.deleteMarkByDate(id, selectedSubject, date);

        return Map.of("result", "success");
    }


    private User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userService.getUserByUsername(username);
    }

}
