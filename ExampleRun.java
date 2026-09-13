package com.gamar.project_netbeans;

/** A console example that does not open windows or write saved tasks. */
public class ExampleRun {
    public static void main(String[] args) {
        toDoList list = new toDoList("My Week", 5);

        System.out.println("=== Add Tasks ===");
        list.addTask(new PersonalTask("Buy groceries", "Milk and fruit"));
        list.addTask(new urgencyLevel("Submit assignment", "2026-10-15", "Review the final draft", "High"));
        WorkTask project = new WorkTask("Prepare presentation", "Team meeting", 2);
        project.addSubTask(new SubTask("Write the outline"));
        project.addSubTask(new SubTask("Create the slides"));
        list.addTask(project);

        System.out.println("\n=== All Tasks ===");
        list.display();

        System.out.println("\n=== Complete a Task ===");
        list.searchTask("Buy groceries").isDone();
        list.searchTask("Buy groceries").display();

        System.out.println("\n=== Duplicate Title ===");
        try {
            list.addTask(new PersonalTask("buy groceries", "Another note"));
        } catch (DuplicateTaskException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=== Remove a Task ===");
        list.removeTask("Prepare presentation");

        System.out.println("\n=== Find a Missing Task ===");
        try {
            list.searchTask("Prepare presentation");
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
