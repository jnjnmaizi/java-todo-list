/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamar.project_netbeans;

/**
 *
 * @author gamaraljaliy
 */
import java.io.*;

public class toDoList implements Serializable {

    private String name;
    private Node head;
    private int size;
    private int maxSize;

    public toDoList(String name, int maxSize) {
        this.name = name;
        this.maxSize = maxSize;
        this.head = null;
        this.size = 0;
    }

    public void addTask(Tasks t) { //add tasks
        if (size >= maxSize) {
            System.out.println("Task list is full!");
            return;
        }

        if (searchOrNull(t.getTitle()) != null) {
            throw new DuplicateTaskException("Task already exists!");
        }

        Node newNode = new Node(t);

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.getNext() != null)
                temp = temp.getNext();
            temp.setNext(newNode);
        }

        size++;
        System.out.println("Task added successfully!");
    }

    public void removeTask(String title) { //remove tasks
        if (head == null) {
            throw new TaskNotFoundException("Task not found!");
        }

        if (head.getData().getTitle().equalsIgnoreCase(title)) {
            head = head.getNext();
            size--;
            System.out.println("Task removed!");
            return;
        }

        Node prev = head;
        Node curr = head.getNext();

        while (curr != null) {
            if (curr.getData().getTitle().equalsIgnoreCase(title)) {
                prev.setNext(curr.getNext());
                size--;
                System.out.println("Task removed!");
                return;
            }
            prev = curr;
            curr = curr.getNext();
        }

        throw new TaskNotFoundException("Task not found!");
    }

    public Tasks searchOrNull(String title) {
        Node temp = head;

        while (temp != null) {
            if (temp.getData().getTitle().equalsIgnoreCase(title))
                return temp.getData();
            temp = temp.getNext();
        }

        return null;
    }

    public Tasks searchTask(String title) {
        Tasks t = searchOrNull(title);
        if (t == null) {
            throw new TaskNotFoundException("Task not found!");
        }
        return t;
    }

    public void display() {
        Node temp = head;

        if (temp == null) {
            System.out.println("No tasks to display.");
            return;
        }

        while (temp != null) {
            temp.getData().display();
            temp = temp.getNext();
        }
    }

  public void saveToFile() {
    try {
        File out = new File("tasks.dat");
        FileOutputStream fos = new FileOutputStream(out);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(this);
        oos.close();
    } catch (IOException e) { //checked Ex. handle in the method where occurred
        System.out.println(e.toString());
    }
}

 public static toDoList readFromFile() {
    try {
        File f = new File("tasks.dat");
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);

        toDoList list = (toDoList) ois.readObject();
        ois.close();
        return list;

    } catch (ClassNotFoundException ex) { //checked Ex. handle in the method where occurred
        System.out.println(ex.toString());
    } catch (IOException e) {//checked Ex. handle in the method where occurred
        System.out.println(e.toString());
    }

    return null;
}
}