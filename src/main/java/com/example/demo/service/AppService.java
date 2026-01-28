package com.example.demo.service;

import com.example.demo.repository.AppRepository;
import com.example.demo.entities.TaskStatus;
import com.example.demo.entities.Task;
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

    public Task createTask(String name, String description, LocalDate deadline) {
        Task newTask = new Task(
                name,
                description,
                deadline,
                TaskStatus.TODO
        );

        return repository.save(newTask);
    }
}




