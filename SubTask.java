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
public class SubTask implements Serializable {
 // class have an compstion relation with Work

	   private String name;
	   
	   
	   public SubTask(String name){ // Constructor
	   this.name=name;
	  
	   }
	   
	   
	   
	   public SubTask(SubTask subTask) {
		this.name=subTask.name;
	}



	   public void display(){
	   System.out.println("SubTask : "+name );
	   }

	   public String getName() {
				return name;
	   }



}// end class