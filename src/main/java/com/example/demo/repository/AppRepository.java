package com.example.demo.repository;
import com.example.demo.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository extends JpaRepository<Task, Integer> {
}
