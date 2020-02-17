package com.SpringStudentEntry.StudentEntries.controller;

import com.SpringStudentEntry.StudentEntries.dto.TeacherDto;
import com.SpringStudentEntry.StudentEntries.entity.Teacher;
import com.SpringStudentEntry.StudentEntries.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/teachers/")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @GetMapping("signup")
    public String showSignUpForm(Model model, Teacher teacher) {
        model.addAttribute("teacher", new TeacherDto());
        return "add-teacher";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("teachers", teacherService.findAll());
        return "index2";
    }

    @PostMapping("add")
    public String addTeacher(@Valid TeacherDto teacher, BindingResult result, Model model) {
        teacherService.create(teacher);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        TeacherDto teacher = teacherService.findById(id);
        model.addAttribute("teacher", teacher);
        return "update-teacher";
    }

    @PostMapping("update/{id}")
    public String updateTeacher(@PathVariable("id") String id, @Valid TeacherDto teacher, BindingResult result,
                                @RequestParam(name = "image", required = false) MultipartFile image, Model model) throws IOException {
        teacherService.update(id, teacher, image);
        model.addAttribute("teachers", teacherService.findAll());
        return "index2";
    }

    @GetMapping("delete/{id}")
    public String deleteTeacher(@PathVariable("id") String id, Model model) {
        teacherService.delete(id);
        model.addAttribute("teachers", teacherService.findAll());
        return "index2";
    }
}
