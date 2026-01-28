Feature: Adding a task
    As a user
    I want to add tasks to my to-do list
    So that I can manage my work

    Scenario: Adding an empty task is invalid
        Given empty task name
        When I try to add this task
        Then it should not work

    Scenario: Adding a task should initially be TODO
        Given a task with name "Buy milk"
        When I add this task
        Then the task status should be "TODO"

    Scenario: Adding a task successfully
        Given a task with name "Buy milk" and description "2 liters"
        When I add this task
        Then the task list should contain "Buy milk" with description "2 liters"
