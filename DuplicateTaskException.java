/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamar.project_netbeans;

/**
 *
 * @author gamaraljaliy
 */
public class DuplicateTaskException extends RuntimeException { //user-defind unchecked Exception
    public DuplicateTaskException(String message) { //handle in main when call method addtask using try-catch
        super(message);
    }
}
