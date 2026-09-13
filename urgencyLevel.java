/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamar.project_netbeans;

/**
 *
 * @author gamaraljaliy
 */
public class urgencyLevel extends ImportantTask {
    
 private String urgencyLevel; // "High", "Medium", "Low"
    
    public urgencyLevel(String title, String deadline, String addNote, String urgencyLevel) {
        super(title, deadline, addNote);
        this.urgencyLevel = urgencyLevel;
    }
    
    @Override
    public void display() {
        System.out.println("=== DEADLINE TASK ===");
        super.display();
        System.out.println("Urgency Level: " + urgencyLevel);
        System.out.println("=====================");
    }
    
    // Getter and Setter
    public String getUrgencyLevel() { return urgencyLevel; }
    public void setUrgencyLevel(String urgencyLevel) { this.urgencyLevel = urgencyLevel; }
}

