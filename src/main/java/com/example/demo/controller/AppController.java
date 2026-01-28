package com.example.demo.controller;

import com.example.demo.dto.TaskForm;
import com.example.demo.service.AppService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class AppController {
    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todoTasks", appService.getTodoTasks());
        model.addAttribute("doneTasks", appService.getDoneTasks());

        return "index";
    }

    @PostMapping("/tasks")
    public String createTask(
            @ModelAttribute TaskForm form,
            Model model
    ) {
        if (form.getName() == null) {
            throw new IllegalArgumentException("Task name cannot be empty");
        }

        appService.createTask(
                form.getName(),
                form.getDescription(),
                form.getDeadline()
        );

        model.addAttribute("todoTasks", appService.getTodoTasks());
        model.addAttribute("doneTasks", appService.getDoneTasks());
        return "index :: #tasksList";
    }

    @PostMapping("/tasks/{id}/done")
    public String markDone(@PathVariable Integer id, Model model) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null!");
        }

        appService.markTaskDone(id);

        model.addAttribute("todoTasks", appService.getTodoTasks());
        model.addAttribute("doneTasks", appService.getDoneTasks());

        return "index :: #tasksList";
    }

    @PostMapping("/tasks/{id}/not-done")
    public String markNotDone(@PathVariable Integer id, Model model) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null!");
        }

        appService.markTaskNotDone(id);

        model.addAttribute("todoTasks", appService.getTodoTasks());
        model.addAttribute("doneTasks", appService.getDoneTasks());

        return "index :: #tasksList";
    }
}