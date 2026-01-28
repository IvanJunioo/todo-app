Feature: Task validation
    As a user
    I want my inputs to be validated
    So that invalid data is not saved

    Scenario: Task name is required
        Given I try to add a task with empty name
        Then the task should not be saved

    Scenario: Task deadline cannot be in the past
        Given I try to add a task with a deadline "2025-01-01"
        Then the task should not be saved