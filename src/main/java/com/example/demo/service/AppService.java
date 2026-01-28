package com.example.demo.service;

import com.example.demo.repository.AppRepository;
import com.example.demo.entities.TaskStatus;
import com.example.demo.entities.Task;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppService {
    private final AppRepository repository;

    public AppService(AppRepository repo) {
        repository = repo;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public void createTask(String name, String description, LocalDate deadline) {
        Task newTask = new Task(
                name,
                description,
                deadline,
                TaskStatus.TODO
        );

        repository.save(newTask);
    }
    @Transactional
    public void markTaskDone(Integer id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus(TaskStatus.DONE);
    }

    @Transactional
    public void markTaskNotDone(Integer id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus(TaskStatus.TODO);
    }

    // helpers
    public List<Task> getTodoTasks() {
        return repository.findByStatus(TaskStatus.TODO);
    }

    public List<Task> getDoneTasks() {
        return repository.findByStatus(TaskStatus.DONE);
    }
}




