package com.gamar.project_netbeans;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddImportantTaskFrame extends JFrame {

    private JTextField titleField, deadlineField, urgencyField;
    private JTextArea noteArea;
    private JButton addButton, cancelButton;
    private toDoList myList;

    public AddImportantTaskFrame(toDoList list) {
        this.myList = list;
        initComponents();
    }

    private void initComponents() {
        setTitle("Add Important Task");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField();

        JLabel deadlineLabel = new JLabel("Deadline:");
        deadlineField = new JTextField();

        JLabel noteLabel = new JLabel("Note:");
        noteArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(noteArea);

        JLabel urgencyLabel = new JLabel("Urgency (optional):");
        urgencyField = new JTextField();

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
        gbc.gridx=0; gbc.gridy=1; panel.add(deadlineLabel, gbc);
        gbc.gridx=1; gbc.gridy=1; panel.add(deadlineField, gbc);
        gbc.gridx=0; gbc.gridy=2; panel.add(noteLabel, gbc);
        gbc.gridx=1; gbc.gridy=2; panel.add(scrollPane, gbc);
        gbc.gridx=0; gbc.gridy=3; panel.add(urgencyLabel, gbc);
        gbc.gridx=1; gbc.gridy=3; panel.add(urgencyField, gbc);
        gbc.gridx=0; gbc.gridy=4; panel.add(addButton, gbc);
        gbc.gridx=1; gbc.gridy=4; panel.add(cancelButton, gbc);

        add(panel);
    }

    private void addTaskAction(ActionEvent e) {
        String title = titleField.getText().trim();
        String deadline = deadlineField.getText().trim();
        String note = noteArea.getText().trim();
        String urgency = urgencyField.getText().trim();

        if(title.isEmpty() || deadline.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title and Deadline cannot be empty!");
            return;
        }

        if(!urgency.isEmpty()) {
            urgencyLevel task = new urgencyLevel(title, deadline, note, urgency);
            myList.addTask(task);
        } else {
            ImportantTask task = new ImportantTask(title, deadline, note);
            try {
                myList.addTask(task);
            } catch (DuplicateTaskException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
        JOptionPane.showMessageDialog(this, "Task added successfully!");
        dispose();
    }
}
