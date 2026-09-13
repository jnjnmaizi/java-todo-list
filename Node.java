/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamar.project_netbeans;

/**
 *
 * @author gamaraljaliy
 */
import java.io.Serializable;

public class Node implements Serializable {

    private Tasks data;
    private Node next;

    public Node(Tasks d) {
        this.data = d;
        this.next = null;
    }

    public Tasks getData() {
        return data;
    }

    public void setData(Tasks data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

