/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamar.project_netbeans;

/**
 *
 * @author gamaraljaliy
 */
public class PersonalTask extends Tasks{

	public PersonalTask(String T , String add) {
		super(T,add);
		
	}


	public void display() {
	    System.out.println("[Personal Task]");
	    System.out.println("Title: " + Title);
	    System.out.println("Note: " + Addnote);
	    System.out.println("Completed: " + isComplete);
	    System.out.println("----------------------");
	}

} //end of class