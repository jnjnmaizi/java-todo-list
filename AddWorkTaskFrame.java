package com.gamar.project_netbeans;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddWorkTaskFrame extends JFrame {

    private JTextField titleField, subtaskField;
    private JTextArea noteArea;
    private JButton addButton, cancelButton;
    private toDoList myList;
    private WorkTask workTask;

    public AddWorkTaskFrame(toDoList list) {
        this.myList = list;
        initComponents();
    }

    private void initComponents() {
        setTitle("Add Work Task");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField();

        JLabel noteLabel = new JLabel("Note:");
        noteArea = new JTextArea(5,20);
        JScrollPane scrollPane = new JScrollPane(noteArea);

        addButton = new JButton("Add Task");
        cancelButton = new JButton("Cancel");

        addButton.addActionListener(this::addTaskAction);
        cancelButton.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx=0; gbc.gridy=0; panel.add(titleLabel, gbc);
        gbc.gridx=1; gbc.gridy=0; panel.add(titleField, gbc);
        gbc.gridx=0; gbc.gridy=1; panel.add(noteLabel, gbc);
        gbc.gridx=1; gbc.gridy=1; panel.add(scrollPane, gbc);
        gbc.gridx=0; gbc.gridy=2; panel.add(addButton, gbc);
        gbc.gridx=1; gbc.gridy=2; panel.add(cancelButton, gbc);

        add(panel);
    }

    private void addTaskAction(ActionEvent e) {
        String title = titleField.getText().trim();
        String note = noteArea.getText().trim();

        if(title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title cannot be empty!");
            return;
        }

        String subtaskCountStr = JOptionPane.showInputDialog(this, "How many subtasks?");
        int subCount;
        try {
            subCount = Integer.parseInt(subtaskCountStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number of subtasks!");
            return;
        }

        workTask = new WorkTask(title, note, subCount);
        for(int i=1;i<=subCount;i++) {
            String subName = JOptionPane.showInputDialog(this, "Enter name for subtask " + i);
            if(subName != null && !subName.trim().isEmpty()) {
                SubTask s = new SubTask(subName.trim());
                workTask.addSubTask(s);
            }
        }

        try {
            myList.addTask(workTask);
            JOptionPane.showMessageDialog(this, "Work Task added successfully!");
            dispose();
        } catch (DuplicateTaskException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
