/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamar.project_netbeans;

/**
 *
 * @author gamaraljaliy
 */
public class ImportantTask extends Tasks {
	private String deadline;

	    public ImportantTask(String title,String d, String addNote) {
	        super(title, addNote);
	        deadline=d;
	    }
	   
	    public void display() {
	        System.out.println(">----- IMPORTANT TASK -----<");
	        System.out.println("Title: " + Title);
	        System.out.println("Note: " + Addnote);
	        System.out.println("DeadLine Due:" + deadline);
	        System.out.println("Completed: " + isComplete);
	        System.out.println(">--------------------------<");
	    }

} //end class
