Feature: Task completion
    As a user
    I want to mark tasks as done or undo them
    So that I can track progress

    Scenario: Mark a task as done
        Given a task "Buy milk" exists and is TODO
        When I mark the task "Buy milk" as done
        Then the task status of "Buy milk" should be "DONE"

    Scenario: Undo a task marked done
        Given a task "Buy milk" exists and is DONE
        When I mark the task "Buy milk" as not DONE
        Then the task status should be "TODO"
