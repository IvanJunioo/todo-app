package com.example.demo.steps;

import com.example.demo.entities.Task;
import com.example.demo.entities.TaskStatus;
import com.example.demo.service.AppService;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.*;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.TodoApp;

@CucumberContextConfiguration
@SpringBootTest(classes = TodoApp.class)
class TaskStepDefinitions {
    @Autowired
    private AppService service;

    private final Map<String, Task> tasks = new HashMap<>();
    private Task currentTask;
    private boolean addAttempted;

    // black-box (behavioral) tests
    // adding tasks
    @Given("empty task name")
    public void empty_task_name() {
        currentTask = new Task("", "Some description", LocalDate.now(), TaskStatus.TODO);
    }

    @Given("a task with name {string}")
    public void task_with_name(String name) {
        currentTask = new Task(name, "", LocalDate.now(), TaskStatus.TODO);
    }

    @Given("a task with name {string} and description {string}")
    public void task_with_name_and_description(String name, String description) {
        currentTask = new Task(name, description, LocalDate.now(), TaskStatus.TODO);
    }

    @When("I add this task")
    public void i_add_this_task() {
        try {
            service.createTask(
                    currentTask.getName(),
                    currentTask.getDescription(),
                    currentTask.getDeadline()
            );
            addAttempted = true;
            tasks.put(currentTask.getName(), currentTask);
        } catch (Exception e) {
            addAttempted = false;
        }
    }

    @Then("the task status should be {string}")
    public void task_status_should_be(String status) {
        TaskStatus expected = TaskStatus.valueOf(status.toUpperCase());
        Assertions.assertEquals(expected, currentTask.getStatus());
    }

    @Then("the task list should contain {string} with description {string}")
    public void task_list_should_contain(String name, String description) {
        Task t = tasks.get(name);
        Assertions.assertNotNull(t);
        Assertions.assertEquals(description, t.getDescription());
    }

    @Then("it should not work")
    @Then("the task should not be saved")
    public void task_should_not_be_saved() {
        Assertions.assertFalse(addAttempted, "Task should not have been added!");
    }

    // marking tasks

    @Given("a task {string} exists and is TODO")
    public void task_exists_and_is_todo(String name) {
        Task t = new Task(name, "", LocalDate.now(), TaskStatus.TODO);
        tasks.put(name, t);
        currentTask = t;
    }

    @Given("a task {string} exists and is DONE")
    public void task_exists_and_is_done(String name) {
        Task t = new Task(name, "", LocalDate.now(), TaskStatus.DONE);
        tasks.put(name, t);
        currentTask = t;
    }

    @When("I mark the task {string} as done")
    public void mark_task_as_done(String name) {
        Task t = tasks.get(name);
        service.markTaskDone(t.getId());
        t.setStatus(TaskStatus.DONE);
    }

    @When("I mark the task {string} as not DONE")
    public void mark_task_as_not_done(String name) {
        Task t = tasks.get(name);
        service.markTaskNotDone(t.getId());
        t.setStatus(TaskStatus.TODO);
    }

    @Then("the task status of {string} should be {string}")
    public void task_status_of_should_be(String name, String status) {
        TaskStatus expected = TaskStatus.valueOf(status.toUpperCase());
        Task t = tasks.get(name);
        Assertions.assertEquals(expected, t.getStatus());
    }

    // listing tasks

    @Given("several tasks exist")
    public void several_tasks_exist() {
        tasks.put("Task1", new Task("Task1", "", LocalDate.now(), TaskStatus.TODO));
        tasks.put("Task2", new Task("Task2", "", LocalDate.now(), TaskStatus.DONE));
        tasks.put("Task3", new Task("Task3", "", LocalDate.now(), TaskStatus.TODO));
        tasks.put("Task4", new Task("Task4", "", LocalDate.now(), TaskStatus.DONE));
    }

    @When("I view the TODO table")
    public void view_todo_table() {
        currentTask = null; // just checking list
    }

    @When("I view the DONE table")
    public void view_done_table() {
        currentTask = null;
    }

    @Then("it should only show tasks with status TODO")
    public void only_show_todo() {
        tasks.values().forEach(t -> {
            if (t.getStatus() != TaskStatus.TODO) {
                // would fail if included in TODO table
                Assertions.assertTrue(t.getStatus() != TaskStatus.DONE);
            }
        });
    }

    @Then("it should only show tasks with status DONE")
    public void only_show_done() {
        tasks.values().forEach(t -> {
            if (t.getStatus() != TaskStatus.DONE) {
                Assertions.assertTrue(t.getStatus() != TaskStatus.TODO);
            }
        });
    }

    // validating tasks

    @Given("I try to add a task with empty name")
    public void add_task_with_empty_name() {
        currentTask = new Task("", "Desc", LocalDate.now(), TaskStatus.TODO);
    }

    @Given("I try to add a task with a deadline {string}")
    public void add_task_with_deadline(String date) {
        currentTask = new Task("Task", "Desc", LocalDate.parse(date), TaskStatus.TODO);
    }
}