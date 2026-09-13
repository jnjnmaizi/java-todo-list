/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamar.project_netbeans;

/**
 *
 * @author gamaraljaliy
 */
public class WorkTask extends Tasks {

	
		   private SubTask[] subTaskList; //Array of subtasks
		   private int size;
		   private int taskCount;
		  
		   public WorkTask(String title, String addNote, int size)
		   { super(title,addNote);
		      this.size = size;
		      this.subTaskList = new SubTask[size];
		      this.taskCount = 0; }
		   
		   
		  
		   public void addSubTask(SubTask subTask) {
			    
			        if (taskCount < size){
			            subTaskList[taskCount] = new SubTask(subTask);
			            taskCount++;
			            System.out.println("Subtask '" + subTask.getName() + "' added to work task.");
			        }}
			 

		   public void display() {
			    System.out.println(">----- WORK TASK -----<");
			    System.out.println("Title: " + Title);
			    System.out.println("Note: " + Addnote);
			    System.out.println("Completed: " + isComplete);
			    System.out.println("Number of Subtasks: " + taskCount);
			    System.out.println("Subtasks:");
			    
			    for (int i = 0; i < taskCount; i++) {
			        subTaskList[i].display();
			    }
			    System.out.println(">----------------------<");
			}

		  
		} //end of class



