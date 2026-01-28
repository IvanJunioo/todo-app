Feature: Task listing
    As a user
    I want to see tasks separated by status
    So that I can focus on pending tasks

    Scenario: Only TODO tasks appear in the TODO table
        Given several tasks exist
        When I view the TODO table
        Then it should only show tasks with status TODO

    Scenario: Only DONE tasks appear in the DONE table
        Given several tasks exist
        When I view the DONE table
        Then it should only show tasks with status DONE
