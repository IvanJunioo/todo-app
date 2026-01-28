package com.example.demo.dto;

import java.time.LocalDate;

public class TaskForm {
    private String name;
    private LocalDate deadline;
    private String description;

    public String getName() {
        return name;
    }
    public LocalDate getDeadline() { return deadline; }
    public String getDescription() {
        return description;
    }

    public void setName(String name) { this.name = name;}
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }
    public void setDescription(String description) { this.description = description; }
}
