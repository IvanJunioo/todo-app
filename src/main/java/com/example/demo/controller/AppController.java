package com.example.demo.controller;

import com.example.demo.service.AppService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class AppController {
    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", appService.getAllTasks());
        return "displayTasks";
    }

    @GetMapping("/create")
    public String create(
            @RequestParam String name,
            @RequestParam String deadline,
            @RequestParam(required = false) String description
            ) {
        LocalDate parsedDate = LocalDate.parse(deadline);
        appService.createTask(name, description, parsedDate);
        return "addTask";
    }
}