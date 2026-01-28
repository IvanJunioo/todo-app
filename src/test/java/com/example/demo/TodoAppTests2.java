package com.example.demo;
import com.example.demo.entities.Task;
import com.example.demo.entities.TaskStatus;
import com.example.demo.repository.AppRepository;
import com.example.demo.service.AppService;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

// white box tests
class TodoAppTests2 {
    private AppRepository repository;
    private AppService services;

    @BeforeEach
    void setup() {
        repository = Mockito.mock(AppRepository.class);
        services = new AppService(repository);
    }

    @Test
    void testCreateTask() {
        services.createTask("Test create", "Desc", LocalDate.now());

        // verify that .save of repository was called only ONCE
        verify(repository, times(1)).save(any(Task.class));
    }

    @Test
    void testMarkAsDone() {
        Task task = new Task("Task1", "Desc1", LocalDate.now(), TaskStatus.TODO);
        Integer id = task.getId();

        when(repository.findById(id)).thenReturn(Optional.of(task));

        services.markTaskDone(id);

        assert task.getStatus() == TaskStatus.DONE;
    }

    @Test
    void testMarkAsNotDone() {
        Task task = new Task("Task1", "Desc1", LocalDate.now(), TaskStatus.TODO);
        Integer id = task.getId();

        when(repository.findById(id)).thenReturn(Optional.of(task));

        services.markTaskNotDone(id);

        assert task.getStatus() == TaskStatus.DONE;
    }
}
