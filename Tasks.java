/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamar.project_netbeans;

import java.io.Serializable;

/**
 *
 * @author gamaraljaliy
 */
public abstract class Tasks implements Serializable {
// class have an Aggregation relation with Work

	   protected String Title ;
	   protected boolean isComplete;
	   protected String Addnote;
	   
	   // constructor
	   public Tasks(String T ,String add){
	   Title = T ;
	   Addnote=add;
	   isComplete=false;}
	

	   public abstract void display(); // overriden becuase it is abstract class
	   
	   
	   public void isDone(){
	   if(!isComplete){
	   isComplete = true;
	   System.out.println("Task : "+ Title + " , marked as done .");
	   
	   }// end if
	   else{
	   System.out.println("Task : "+ Title + " , is alraedy done .");
	   } // end else  
	   
	   
	   }// end method
	   
	   public String getTitle() {
		   return Title;
	   }
	   
	 

	} // end class