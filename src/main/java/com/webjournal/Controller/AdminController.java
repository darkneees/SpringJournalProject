package com.webjournal.Controller;

import com.webjournal.Entity.Teacher;
import com.webjournal.Service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    TeacherServiceImpl teacherService;

    @GetMapping("/admin")
    public String getAdminPage(Model model){

        List<Teacher> teacherList = teacherService.getAllTeacher();
        System.out.println(teacherList.toString());
        model.addAttribute("teacher_list", teacherList);

        return "admin";
    }

}
