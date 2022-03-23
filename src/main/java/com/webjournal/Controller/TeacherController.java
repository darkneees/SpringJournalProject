package com.webjournal.Controller;

import com.webjournal.Entity.Pupil;
import com.webjournal.Entity.Teacher;
import com.webjournal.Service.PupilServiceImpl;
import com.webjournal.Service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class TeacherController {

    @Autowired
    TeacherServiceImpl teacherService;

    @Autowired
    PupilServiceImpl pupilService;

    @GetMapping("/teacher")
    public String getTeacherPage(Model model){
        model.addAttribute("teacher", getUserTeacher());
        return "teacher";
    }

    @PostMapping("/teacher")
    public String getTeacherPageWithTableClass(Model model, @RequestParam("selectedClass") String selectedClass,
                                               @RequestParam("selectedSubject") String selectedSubject) {
        model.addAttribute("teacher", getUserTeacher());
        List<Pupil> pupils = pupilService.getAllPupilsByClass(selectedClass);
        for(Pupil pupil: pupils) {
            Map<String, Object> temp = (Map<String, Object>)
                    ((Map<String, Object>)
                            pupil.getMarks().get("marks")).get(selectedSubject);
            pupil.setCurrentSubjectMarks(temp);
            System.out.println(pupil.getCurrentSubjectMarks());
        }

        model.addAttribute("pupils", pupils);

        return "teacher";

    }

    private Teacher getUserTeacher(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return teacherService.getTeacherByUsername(authentication.getName());
    }

}
