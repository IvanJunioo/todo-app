package com.example.demo.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;
    private LocalDate deadline;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    protected Task() {}

    public Task(String name, String description, LocalDate deadline, TaskStatus status) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
    }

    // getters
    public Integer getId() {return this.id;}
    public String getName() {return this.name;}
    public String getDescription() {return this.description;}
    public String getDeadline() {return this.deadline.toString();}
    public TaskStatus getStatus() {return this.status;}

    // setters
    public void setStatus(TaskStatus status) {
        assert status != null;
        this.status = status;
    }
}
