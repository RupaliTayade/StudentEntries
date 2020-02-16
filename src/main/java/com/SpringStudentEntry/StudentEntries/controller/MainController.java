package com.SpringStudentEntry.StudentEntries.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String start() {
        return "redirect:/students/list";
    }
}
