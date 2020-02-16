package com.SpringStudentEntry.StudentEntries.controller;

import com.SpringStudentEntry.StudentEntries.dto.StudentDto;
import com.SpringStudentEntry.StudentEntries.service.StudentService;
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
@RequestMapping("/students/")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("student", new StudentDto());
        return "add-student";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "index";
    }

    @PostMapping("add")
    public String addStudent(StudentDto student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-student";
        }

        studentService.create(student);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        StudentDto student = studentService.findById(id);
        model.addAttribute("student", student);
        model.addAttribute("teachers", teacherService.findAll());

        return "update-student";
    }

    @PostMapping("update/{id}")
    public String updateStudent(@PathVariable("id") String id, @Valid StudentDto student, BindingResult result,
                                @RequestParam(name = "image", required = false) MultipartFile image,
                                @RequestParam(name = "teacherId", required = false) String teacherId, Model model) throws IOException {
        studentService.update(teacherId, id, student, image);
        model.addAttribute("students", studentService.findAll());
        return "index";
    }

    @DeleteMapping("delete/{id}")
    public String deleteStudent(@PathVariable("id") String id, Model model) {
        studentService.delete(id);
        model.addAttribute("students", studentService.findAll());
        return "index";
    }
}
