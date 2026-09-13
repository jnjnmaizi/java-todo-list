/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamar.project_netbeans;

import java.util.Scanner;

public class testToDo {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        //Load automatically at start
        toDoList myList = toDoList.readFromFile();

        System.out.print("Enter your ToDo List name: ");
            String listName = input.nextLine();

            System.out.print("Enter max number of tasks: ");
            int size = input.nextInt();
            input.nextLine();

            myList = new toDoList(listName, size);
        

         //Start GUI 
        firstFrame fram = new firstFrame(myList);
        fram.setVisible(true);

        int choice = -1;
        while (choice != 7) {

            System.out.println("\n====== ToDo ======");
            System.out.println("1. Add Personal Task");
            System.out.println("2. Add Important Task");
            System.out.println("3. Add Work Task with Subtasks");
            System.out.println("4. Display All Tasks");
            System.out.println("5. Remove Task");
            System.out.println("6. Mark Task as Done");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter task title: ");
                    String t1 = input.nextLine();
                    System.out.print("Enter note: ");
                    String note1 = input.nextLine();
                    PersonalTask p = new PersonalTask(t1, note1);

                    try {
                        myList.addTask(p);
                    } catch (DuplicateTaskException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter task title: ");
                    String t2 = input.nextLine();
                    System.out.print("Enter deadline date: ");
                    String d2 = input.nextLine();
                    System.out.print("Enter note: ");
                    String note2 = input.nextLine();
                    System.out.print("Do you want to add urgency level? (yes/no): ");
                    String hasUrgency = input.nextLine();

                    if (hasUrgency.equalsIgnoreCase("yes")) {
                        System.out.print("Enter urgency level (High/Medium/Low): ");
                        String u2 = input.nextLine();
                        urgencyLevel task = new urgencyLevel(t2, d2, note2, u2);
                        myList.addTask(task);
                    } else {
                        ImportantTask task = new ImportantTask(t2, d2, note2);
                        try {
                            myList.addTask(task);
                        } catch (DuplicateTaskException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter work task title: ");
                    String wt = input.nextLine();
                    System.out.print("Enter note: ");
                    String wn = input.nextLine();
                    System.out.print("How many subtasks? ");
                    int subNum = input.nextInt();
                    input.nextLine();

                    WorkTask wtask = new WorkTask(wt, wn, subNum);

                    for (int i = 1; i <= subNum; i++) {
                        System.out.print("Enter subtask " + i + " name: ");
                        String subName = input.nextLine();
                        SubTask s = new SubTask(subName);
                        wtask.addSubTask(s);
                    }

                    try {
                        myList.addTask(wtask);
                    } catch (DuplicateTaskException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    myList.display();
                    break;

                case 5:
                    System.out.print("Enter task title to remove: ");
                    String rm = input.nextLine();
                    myList.removeTask(rm);
                    break;

                case 6:
                    try {
                        System.out.print("Enter task title to mark as done: ");
                        String doneTitle = input.nextLine();
                        Tasks t = myList.searchTask(doneTitle);
                        t.isDone();
                        myList.saveToFile(); // Auto-save
                        System.out.println("Task marked as done!");
                    } catch (TaskNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 7:
                    myList.saveToFile(); // Auto-save on exit
                    System.out.println("Saved! Exiting... Goodbye");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
