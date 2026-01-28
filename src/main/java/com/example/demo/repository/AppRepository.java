package com.example.demo.repository;
import com.example.demo.entities.Task;
import com.example.demo.entities.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppRepository extends JpaRepository<Task, Integer> {
    List<Task> findByStatus(TaskStatus status);
}
